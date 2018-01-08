var vm = new Vue({
    el: "#content",
    data: {
        companyId: 0,
        accountNumber: "",
        merchantName: "",//商户名称
        abbreviation: "",//简称
        companyLogo: "",
        tel: "",//手机号
        bindingEmail: "", //绑定邮箱
        authStatus: 0,  //企业认证状况  0 未认证 1.企业已经认证
        name: "",//姓名
        nameTel: "",
        binding: 1,//是否绑定微信   1==已绑定       2==未绑定
        codeTimeOne: 60,//倒计时
        codeTimeTwo: 60,//倒计时
        flog: true,//倒计时开关
        flogOne: true,//倒计时开关
        WechatNumber: "微信号",
        WechatNumberStatus: 1,//直接找我 ，备注说不清
        newWechatNumberStatus: 1,//直接找我 ，备注说不清
        authCodeToken: "",
        authCodeSendState: false,
        accountId:0
    },
    methods: {
        downAll: function () {
            $(".position").hide();
            $(".zhe").hide();
        },
        /**
         * @return {boolean}
         * @return {boolean}
         */
        Contacts: function (num) {
            var that = this;
            //num==1--编辑联系人         num==2--保存编辑联系人      num==3--取消编辑
            var cName = $("#name").text();
            var cTel = $("#nameTel").text();
            var cEmail = $("#bindingEmail").text();
            if (num === 1) {
                $("#contactsName").val(cName);
                $("#contactsTel").val(cTel);
                $("#contactsEmail").val(cEmail);
                $(".ContactsCont").show();
                $(".zhe").show();
            } else if (num === 2) {
                if ($("#contactsName").val() === "") {
                    layer.msg("联系人姓名不能为空");
                    return false;
                }
                if ($("#contactsTel").val() === "") {
                    layer.msg("手机号不能为空");
                    return false;
                }
                var myreg = /1[3-8]\d{9}/;
                if (!myreg.test($("#contactsTel").val().trim()) || $("#contactsTel").val().trim().length > 11) {
                    layer.msg("手机号格式错误");
                    return false;
                }
                that.editContacts($("#contactsName").val(), $("#contactsTel").val(), $("#contactsEmail").val());
            } else {
                $(".ContactsCont").hide();
                $(".zhe").hide();
            }
        },
        editContacts: function (cname, ctel, cemail) {
            $.ajax({
                url: "/company/editContacts",
                type: 'POST',
                async: false,
                data: {
                    "id": $("#companyId").val(), "contactsName": cname,
                    "contactsMobile": ctel, "email": cemail
                },
                dataType: "json",
                traditional: true,
                error: function () {
                    layer.msg("服务器异常");
                },
                success: function (data) {
                    $(".ContactsCont").hide();
                    $(".zhe").hide();
                    if (data.status === 1) {
                        layer.msg("更新联系人成功");
                        $("#name").text(data.company.contactsName);
                        $("#nameTel").text(data.company.contactsMobile);
                        $("#bindingEmail").text(data.company.email);
                    } else {
                        layer.msg("更新联系人失败");
                    }
                }
            });
        },
        BindMobilePhone: function (num) {
            var that = this;
            //num==1--绑定手机号         num==2--提交
            var obmp = $("#tel").text();
            if (num === 1) {
                $("#obmp").val(obmp);
                $(".BindMobilePhone").show();
                $(".zhe").show();
            } else if (num === 2) {
                if ($("#codeOne").val() === "") {
                    layer.msg("请输入验证码");
                    return false;
                }
                that.checkCode(that.authCodeToken, $("#codeOne").val());
            } else {
                $(".BindMobilePhone").hide();
                $(".zhe").hide();
            }
        },
        codeOne: function () {
            if (vm.flog) {
                vm.flog = false;
                $(".codeTimeOne").show();
                var timer = setInterval(function () {
                    vm.codeTimeOne--;
                    if (vm.codeTimeOne === 0) {
                        clearInterval(timer);
                        vm.codeTimeOne = 60;
                        vm.flog = true;
                        $(".codeTimeOne").hide();
                    }
                }, 1000);
                this.sendCode($("#tel").text());
            }
        },
        codeTwo: function () {
            if (vm.flogOne) {
                vm.flogOne = false;
                $(".codeTimeTwo").show();
                var timer = setInterval(function () {
                    vm.codeTimeTwo--;
                    if (vm.codeTimeTwo === 0) {
                        clearInterval(timer);
                        vm.codeTimeTwo = 60;
                        vm.flogOne = true;
                        $(".codeTimeTwo").hide();
                    }
                }, 1000);
                var newTel = $("#newTel").val();
                if (newTel == "") {
                    layer.msg("请输入手机后");
                    return false;
                }
                var myreg = /1[3-8]\d{9}/;
                if (!myreg.test(newTel.trim()) || newTel.trim().length > 11) {
                    layer.msg("手机号格式错误");
                    return false;
                }
                this.sendCode(newTel);
            }
        },
        sendCode: function (mobile) {
            var that = this;
            var flag = false;
            $.ajax({
                url: "/register/getAuthCode",
                type: 'POST',
                async: false,
                data: {"mobile": mobile},
                dataType: "json",
                error: function (e) {
                    layer.msg("获取验证码时发生错误");
                },
                success: function (data) {
                    if (data.data != null && data.data != "" && data.status == "200") {
                        that.authCodeToken = data.data;
                        that.authCodeSendState = true;
                        flag = true;
                    }
                    if (data.msg=="ok"){
                        layer.msg("发送验证码成功")
                    }
                }
            });
            return flag;
        },
        checkCode: function (authCodeToken, authCode) {
            var flag = false;
            $.ajax({
                url: "/register/verifyAuthCode",
                type: 'POST',
                async: false,
                data: {"authCodeToken": authCodeToken, "authCode": authCode},
                dataType: "json",
                error: function () {
                    layer.msg("校验验证码出错了！");
                },
                success: function (data) {
                    if (data) {
                        $(".BindMobilePhone").hide();
                        $(".newBindMobilePhone").show();
                    } else {
                        layer.msg("验证码输入错误");
                    }
                    flag = data;
                }
            });
            return flag;
        },
        newBindMobilePhone: function () {
            var that = this;
            $.ajax({
                url: "/register/verifyAuthCode",
                type: 'POST',
                async: false,
                data: {"authCodeToken": that.authCodeToken, "authCode": $("#codeTwo").val()},
                dataType: "json",
                error: function () {
                    layer.msg("校验验证码出错了！");
                },
                success: function (data) {
                    if (data) {
                        that.updateMobilePhone($("#newTel").val(),$("#accountId").val());
                    } else {
                        layer.msg("验证码输入错误");
                    }
                }
            });
        },
        updateMobilePhone: function (newTel, accountId) {
            var that = this;
            if (newTel == ""){
                layer.msg("请输入手机号");
                return false;
            }
            var myreg = /1[3-8]\d{9}/;
            if (!myreg.test(newTel.trim()) || newTel.trim().length > 11) {
                layer.msg("手机号格式错误");
                return false;
            }
            $.ajax({
                url: "/company/bindMobilePhone",
                type: 'POST',
                async: false,
                data: {"mobile": newTel, "id": accountId},
                dataType: "json",
                error: function () {
                    layer.msg("绑定手机号错误！");
                },
                success: function (data) {
                    if (data) {
                        layer.msg("绑定手机成功");
                        $("#tel").text(newTel);
                        that.downAll();
                    } else {
                        layer.msg("绑定手机号错误");
                    }
                }
            });
        },
        bindwx: function () {
            $(".bindwx").show();
            $(".zhe").show();
        },
        newbindwx: function () {
            $(".newbindwx").show();
            $(".zhe").show();
        },
        Authentication: function () {
            window.location.href = "/company/EnterpriseCertification?type=1";
        }
    }
});

//获取企业信息
var accountToken = sessionStorage.getItem("accountToken");
$.ajax({
    url: "/company/accountToken",
    type: 'POST',
    async: false,
    data: {"accountToken": accountToken},
    dataType: "json",
    traditional: true,
    error: function () {
    },
    success: function (data) {;
        vm.accountNumber = data.account.accountNumber;
        vm.tel = data.account.mobile;
        vm.merchantName = data.company.companyName;
        vm.abbreviation = data.company.companyAbbreviation;
        vm.bindingEmail = data.company.email;
        vm.name = data.company.contactsName;
        vm.nameTel = data.company.contactsMobile;
        if (data.company.authenticationStatus != null && data.company.authenticationStatus != "") {
            vm.authStatus = data.company.authenticationStatus;
        }
        if (data.company.companyLogo != null && data.company.companyLogo != "") {
            vm.companyLogo = data.company.companyLogo;
        }
        vm.companyId = data.company.id;
        vm.accountId =  data.account.id;
    }
});

//上传企业logo
function asyncUpload(inp_id, img_id, hid_id) {
    var companyId = $("#companyId").val();
    var index;
    $("#" + inp_id).upload({
        url: '/company/upload',
        params: {"companyId": companyId},
        dataType: 'json',
        onSend: function (obj, str) {
            index = layer.load(1, {shade: [0.1, '#fff']});
            return true;
        },
        onComplate: function (data) {
            layer.close(index);
           // $("#" + img_id).attr('src', data.reurl);
            vm.companyLogo = data.reurl;
            //  $("#" + hid_id).val(data.reurl);
            //  $('#contractFileName').attr('href', '/contract/contractDownLoad?fileName=' + data.reurl);
            //  $("#contractFileName").text(fileName);
        }
    });
    $("#" + inp_id).upload("ajaxSubmit");
}