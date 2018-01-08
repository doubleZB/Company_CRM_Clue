var vm = new Vue({
    el: "#content",
    data: {
        status: 0, //0未认证  1==已认证  2认证失败
        //show: $("#authStatus").val()
        companyInfo: [],
        authInfo: []
    },
    created: function (){
        var that = this;
        //获取企业信息
        var accountToken = sessionStorage.getItem("accountToken");
        $.ajax({
            url: "/company/authInfo",
            type: 'POST',
            async: false,
            data: {"accountToken": accountToken},
            dataType: "json",
            traditional: true,
            error: function () {
            },
            success: function (data) {
                if (data) {
                    that.companyInfo = data.company;
                    that.authInfo = data.authenticationInformation;
                    $("#authStatus").val(data.company.authenticationStatus);
                    $("#companyId").val(data.company.id);
                    that.status = data.company.authenticationStatus;
                } else {
                    layer.msg("获取企业信息失败");
                }
            }
        });
    },
    methods: {
        Authentication: function () {
            this.status = 0;
        },
        empty: function () {
            $("#ImgUp").attr("src", "");
            $(".name").val("");
            $(".number").val("");
            $("#licenseImg").val("");
            $("#licenseName").val("");
        },
        //获取企业以及企业认证信息
        authCompanyInfo: function () {

        }
        ,
        //更新或插入认证信息
        updateInfo: function (type) {
            var that = this;
            $.ajax({
                url: "/company/updateInfo",
                type: 'POST',
                async: false,
                data: {
                    "companyId": $("#companyId").val(), "companyName": $("#companyName").val(),
                    "authId": $("#authId").val(), "licenseNumber": $("#licenseNumber").val(),
                    "licenseImg": $("#licenseImg").val(), "licenseName": $("#licenseName").val()
                },
                dataType: "json",
                error: function () {
                    layer.msg("企业认证失败");
                },
                success: function (data) {
                    var type = $("#type").val();
                    if (data) {
                        layer.msg("企业认证申请成功", {time: 2000}, function () {
                            if (type=='1'){
                                window.location.href = "/company/companyInfo"
                            }else {
                                window.location.href = "/company/EnterpriseCertification"
                            }

                        });
                    } else {
                        layer.msg("企业认证失败");
                    }
                }
            });
        },
        //提交信息
        submit: function () {
            if ($("#companyName").val() == "") {
                layer.msg("公司全称不能为空");
                return false;
            }
            if ($("#licenseNumber").val() == "") {
                layer.msg("营业执照编号不能为空");
                return false;
            }
            if ($("#licenseImg").val() == "") {
                layer.msg("营业执照副本不能为空");
                return false;
            }
            this.updateInfo();
        }

    }
});

//vm.authCompanyInfo();

//上传企业营业执照
function asyncUpload(inp_id, img_id, hid_id) {
    var index;
    $("#" + inp_id).upload({
        url: '/company/uploadAuthInfo',
        params: {},
        dataType: 'json',
        onSend: function (obj, str) {
            index = layer.load(1, {shade: [0.1, '#fff']});
            return true;
        },
        onComplate: function (data) {
            layer.close(index);
            $("#" + img_id).attr('src', data.reurl);
            $("#" + hid_id).val(data.url);
            $("#licenseName").val(data.licenseName);
            //  $('#contractFileName').attr('href', '/contract/contractDownLoad?fileName=' + data.reurl);
            //  $("#contractFileName").text(fileName);
        }
    });
    $("#" + inp_id).upload("ajaxSubmit");
}

function fileUp(obj) {
    $("#ImgUp").attr("src", getObjectURL(obj.files[0]));
}

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

