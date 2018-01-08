var vm=new Vue({
    el:"#content",
    data:{
        authCodeSendState:false,
        authCodeToken:"",
    }
})

//var authCodeSendState = false;//是否发送验证码
//
//var authCodeToken = null;//验证码Token

$("#accountNumber").on("blur",function(){
    var sta = /^[a-zA-Z][\S]*$/;
    if(sta.test($(this).val()) && $(this).val().trim().length < 21 && $(this).val().trim().length > 5){
        $(".tipTxt").text("");
        if(!verifyAccountNumber($(this).val())){
            $(".tipTxt").text("账户名已被注册请重新输入");
        }
    }else{
        $(".tipTxt").text("账户名格式不正确");
    }
})

$("#password").on("blur",function(){
    if($(this).val().trim().length < 11 && $(this).val().trim().length > 3){
        var reg = /^(?=.*\d)(?=.*[a-zA-Z]).{4,10}$/;
        if (!reg.test($("#password").val())) {
            layer.msg('密码格式不正确请从新输入！');
        }else {
            $(".tipTxt").text("");
        }
    }else{
        $(".tipTxt").text("密码格式不正确请从新输入");
    }
})

$("#mobile").on("blur",function(){
    var sta = /1[3-8]\d{9}/;
    if(sta.test($(this).val()) && $(this).val().trim().length < 12){
        $(".tipTxt").text("");
    }else{
        $(".tipTxt").text("绑定手机号码错误，请重新输入");
    }
})

//校验手机及获取验证码
function mobileAndAuthCode(){
    //校验手机格式
    var mobile = $("#mobile").val();
    if (mobile.trim() != '' && mobile.trim().length > 11) {
        layer.msg('请输入有效的手机号码！');
        return false;
    }

    var myreg = /1[3-8]\d{9}/;
    if (!myreg.test(mobile)) {
        layer.msg('请输入有效的手机号码！');
        return false;
    }

    //校验手机号是否存在
    if (!verifyMobile(mobile)) {
        $(".tipTxt").text("绑定手机号码已存在，请重新输入");
        return false;
    }else {
        $(".tipTxt").text("");
    }

    //获取验证码
    if (!getAuthCode(mobile)) {
			layer.msg('验证码获取失败，请重新获取！');
    }
}

//验证账号是否存在 可用返回true
function verifyAccountNumber(accountNumber){
    var flag = false;
    $.ajax({
        url: "/register/verifyAccountNumber",
        type: 'POST',
        async: false,
        data: {"accountNumber": accountNumber},
        dataType: "json",
        error: function () {
            layer.msg("校验手机号是否存在时发生错误！");
        },
        success: function (data) {
            flag = data;
        }
    });
    return flag;
}

//校验手机号是否存在  可用返回true
function verifyMobile(mobile) {
    var flag = false;
    $.ajax({
        url: "/register/verifyMobile",
        type: 'POST',
        async: false,
        data: {"mobile": mobile},
        dataType: "json",
        error: function () {
            layer.msg("校验手机号是否存在时发生错误！");
        },
        success: function (data) {
            flag = data;
        }
    });
    return flag;
}

//获取验证码  成功返回data.data = authCodeToken
function getAuthCode(mobile) {
    var flag = false;
    $.ajax({
        url: "/register/getAuthCode",
        type: 'POST',
        async: false,
        data: {"mobile": mobile},
        dataType: "json",
        error: function (e) {
            layer.msg("获取验证码时发生错误！");
        },
        success: function (data) {
            if(data.data != null && data.data != "" && data.status == "200"){
                vm.authCodeToken = data.data;
                vm.authCodeSendState = true;
                flag = true;
                layer.msg("获取验证码成功！");
                $("#mobileAndAuthCode").hide();
                $(".getCode").show();
                var time=60;
                var timer=setInterval(function(){
                    time--;
                    $(".getCode > span").text(time);
                    if(time<=0){
                        clearInterval(timer);
                        $("#mobileAndAuthCode").show();
                        $(".getCode").hide();
                        $(".getCode > span").text("60");
                    }
                },1e3)
            }
        }
    });
    return flag;
}

//校验验证码  正确返回true
function verifyAuthCode(authCodeToken,authCode) {
    var flag = false;
    $.ajax({
        url: "/register/verifyAuthCode",
        type: 'POST',
        async: false,
        data: {"authCodeToken": authCodeToken,"authCode":authCode},
        dataType: "json",
        error: function () {
            layer.msg("校验验证码出错了！");
        },
        success: function (data) {
            flag = data;
        }
    });
    return flag;
}

//注册
function register() {
    var accountNumber = $("#accountNumber").val();
    var sta = /^[a-zA-Z][\S]*$/;
    if (!(sta.test($("#accountNumber").val()) && accountNumber.trim().length < 21 && accountNumber.trim().length > 5)) {
        layer.msg('账户名格式不正确！');
        return false;
    }
    if(!verifyAccountNumber(accountNumber)){
        layer.msg('账户名已被注册请重新输入！');
        return false;
    }
    var password = $("#password").val();
    var reg = /^(?=.*\d)(?=.*[a-zA-Z]).{4,10}$/;
    if (!reg.test(password)) {
        layer.msg('密码格式不正确请从新输入！');
        return false;
    }
    if (!($("#password").val().trim().length < 11 && $("#password").val().trim().length > 3)) {
        layer.msg('密码格式不正确请从新输入！');
        return false;
    }
    var mobile = $("#mobile").val();
    mobile = mobile.replace(/(^\s+)|(\s+$)/g, "");
    if (mobile.trim() != '' && mobile.trim().length > 11) {
        layer.msg("请输入有效的手机号码!");
        return false;
    }
    var mobileReg = /1[3-8]\d{9}/;
    if (!mobileReg.test(mobile)) {
        layer.msg('请输入有效的手机号码！');
        return false;
    }
    //校验手机号是否存在
    if (!verifyMobile(mobile)) {
        layer.msg('绑定手机号码已存在，请直接登录！');
        sessionStorage.setItem("register_alreadyRegistered_mobile", mobile);
        window.location.href = "/register/alreadyRegistered";
        return false;
    }
    if (vm.authCodeSendState) {
        var authCode = $("#authCode").val();
        authCode = authCode.replace(/(^\s+)|(\s+$)/g, "");
        if (authCode == "" || authCode.length < 1) {
            layer.msg("请输入验证码!");
            return false;
        }
        var authCodeReg = /^\d{6}$/;
        if (!authCodeReg.test(authCode)) {
            layer.msg("请输入正确的6位验证码!");
            return false;
        }
        if (!$("#agree").is(':checked')) {
            layer.msg("请同意《人脉旺服务条款》");
            return false;
        }
        if(!verifyAuthCode(vm.authCodeToken,authCode)){
            layer.msg("验证码错误!");
            return false;
        }
        $.ajax({
            url: "/register/register",
            type: 'POST',
            async: false,
            data: {"accountNumber":accountNumber,"password":password,"mobile":mobile},
            dataType: "json",
            error: function () {
				layer.msg("注册出错了！");
            },
            success: function (data) {
                //注册成功自动登录
                $.ajax({
                    url: "/login/loginRegister",
                    type: 'POST',
                    async: false,
                    data: {"accountNumber":data.accountNumber,"password":data.password},
                    dataType: "json",
                    error: function () {
                        layer.msg("注册出错了！");
                        window.location.href = "/login/loginFail";
                    },
                    success: function (data_1) {
                        if(data_1 == "false"){
                            layer.msg("注册自动登录失败了，请直接登录!");
                            window.location.href = "/login/loginFail";

                        }else {
                            sessionStorage.setItem("accountToken", data_1.data);
                            sessionStorage.setItem("register_MerchantInformation_id", data.id);
                            sessionStorage.setItem("register_MerchantInformation_mobile", data.mobile);
                            window.location.href = "/registerAddMsg/MerchantInformation";
                        }
                    }
                });
            }
        });
    } else {
        layer.msg("请先获取验证码!");
    }
}