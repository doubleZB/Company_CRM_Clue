var vm=new Vue({
    el:"#content",
    data:{
        ForgotPassword_PasswordRetrieval_mible:"",
        ForgotPassword_PasswordRetrieval_authCodeSendState:"",
    }
})
$(function(){
    vm.ForgotPassword_PasswordRetrieval_mible = sessionStorage.getItem("ForgotPassword_PasswordRetrieval_mible");
    vm.ForgotPassword_PasswordRetrieval_authCodeSendState = sessionStorage.getItem("ForgotPassword_PasswordRetrieval_authCodeSendState");
    if(!sessionStorage.getItem("ForgotPassword_PasswordRetrieval_authCodeSendState")){
        window.location.href = "/login/loginFail"
    }
    //没有验证手机号的话跳转到登录页面
    if(sessionStorage.getItem("ForgotPassword_PasswordRetrieval_mible") == null || sessionStorage.getItem("ForgotPassword_PasswordRetrieval_mible") == ""){
        window.location.href = "/login/loginFail"
    }
    $("#contactsMobile").val(vm.register_MerchantInformation_mobile);
})
function next_2(){
    var password = $("#password").val();
    var reg = /^(?=.*\d)(?=.*[a-zA-Z]).{4,10}$/;
    if (!reg.test(password)) {
        layer.msg('密码格式不正确请从新输入！');
        return ;
    }
    if (!($("#password").val().trim().length < 11 && $("#password").val().trim().length > 3)) {
        layer.msg('密码格式不正确请从新输入！');
        return ;
    }
    var newpassword = $("#newpassword").val();
    if(newpassword != password){
        layer.msg("密码不一致，请从新输入");
        return ;
    }
    $.ajax({
        url: "/login/newPassword",
        type: 'POST',
        async: false,
        data: {"password": password,"mobile":vm.ForgotPassword_PasswordRetrieval_mible},
        dataType: "json",
        error: function () {
            layer.msg("修改密码时发生错误！");
        },
        success: function (data) {
            layer.msg("修改密码成功，跳转登录页面")
            $.ajax({
                url: "/register/sentMsg",
                type: 'POST',
                async: false,
                data: {"mobile":vm.ForgotPassword_PasswordRetrieval_mible},
                dataType: "json",
                error: function () {
                },
                success: function (data) {
                }
            });
            window.location.href = "/login/loginIndex"
        }
    });
}