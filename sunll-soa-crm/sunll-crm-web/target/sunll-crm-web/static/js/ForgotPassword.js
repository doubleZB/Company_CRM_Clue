var vm=new Vue({
    el:"#content",
    data:{
        authCodeSendState:false,
        authCodeToken:"",
    }
})

//校验手机及获取验证码
function mobileAndAuthCode(){
    //校验手机格式
    var mobile = $("#mobile").val();
    if (mobile.trim() != '' && mobile.trim().length > 11) {
        layer.msg('绑定手机号码错误，请重新输入！');
        return false;
    }

    var myreg = /1[3-8]\d{9}/;
    if (!myreg.test(mobile)) {
        layer.msg('请输入有效的手机号码！');
        return false;
    }

    //校验手机号是否存在
    if (verifyMobile(mobile)) {
        layer.msg('手机号不存在！');
        return false;
    }

    //获取验证码
    if (!getAuthCode(mobile)) {
        layer.msg('验证码获取失败，请重新获取！');
    }

}

//校验验证码之后 下一步
function next_1() {
    var mobile = $("#mobile").val();
    mobile = mobile.replace(/(^\s+)|(\s+$)/g, "");
    if (mobile == "" || mobile.length < 1) {
        layer.msg("请输入手机号码!");
        return false;
    }
    var mobileReg = /1[3-8]\d{9}/;
    if (!mobileReg.test(mobile)) {
        layer.msg('请输入有效的手机号码！');
        return false;
    }
    //校验手机号是否存在
    if (verifyMobile(mobile)) {
        layer.msg('手机不存在！');
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
        if(!verifyAuthCode(vm.authCodeToken,authCode)){
            layer.msg("验证码错误!");
            return false;
        }
        sessionStorage.setItem("ForgotPassword_PasswordRetrieval_authCodeSendState", true);
        sessionStorage.setItem("ForgotPassword_PasswordRetrieval_mible", mobile);
        window.location.href = "/login/resetPassword"

    } else {
        layer.msg("请先获取验证码!");
    }
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