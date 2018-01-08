var vm=new Vue({
    el:"#content",
    data:{
        register_MerchantInformation_id:"",
        register_MerchantInformation_mobile:""
    }
})
$(function(){
    vm.register_MerchantInformation_id = sessionStorage.getItem("register_MerchantInformation_id");
    vm.register_MerchantInformation_mobile = sessionStorage.getItem("register_MerchantInformation_mobile");
    //没有获取到注册自动登录账户id，跳转到登录页面  只判断一个就行
    if(sessionStorage.getItem("register_MerchantInformation_id") == null || sessionStorage.getItem("register_MerchantInformation_id") == ""){
        parent.window.location.href = "/login/loginFail"
    }
    $("#contactsMobile").val(vm.register_MerchantInformation_mobile);
})

$("#companyName").on("blur",function(){
    if($(this).val().trim() != '' && $(this).val().trim().length < 31){
        $(".tipTxt").text("");
        if(!verifycompanyName($(this).val())){
            $(".tipTxt").text("该商户名称已被注册，请重新输入");
        }
    }else{
        $(".tipTxt").text("该商户名称不合法，请从新输入");
    }
})

$("#contactsMobile").on("blur",function(){
    var sta = /1[3-8]\d{9}/;
    if(sta.test($(this).val()) && $(this).val().trim().length < 12){
        $(".tipTxt").text("");
    }else{
        $(".tipTxt").text("绑定手机号码错误，请重新输入");
    }
})

$("#email").on("blur",function(){
    var sss = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if(sss.test($(this).val())){
        $(".tipTxt").text("");
    }else{
        $(".tipTxt").text("邮箱不合法，请重新输入");
    }
})

//验证商户名称是否存在 可用返回true
function verifycompanyName(companyName){
    var flag = false;
    $.ajax({
        url: "/register/verifycompanyName",
        type: 'POST',
        async: false,
        data: {"companyName": companyName},
        dataType: "json",
        error: function () {
            layer.msg("校验商户名称是否存在时发生错误")
        },
        success: function (data) {
            flag = data;
        }
    });
    return flag;
}

function submit_1(){
    var companyName = $("#companyName").val();
    if (!(companyName.trim() != '' && companyName.trim().length < 31)) {
        layer.msg("企业名不合法")
        return false;
    }
    if(!verifycompanyName(companyName)){
        layer.msg("该商户名称已被注册请重新输入")
        return false;
    }
    var contactsName = $("#contactsName").val();
    var contactsMobile = $("#contactsMobile").val();
    var sta = /1[3-8]\d{9}/;
    if (!(sta.test($("#contactsMobile").val()) && $("#contactsMobile").val().trim().length < 12)) {
        layer.msg("请输入手机号码")
        return false;
    }
    var email = $("#email").val();
    $.ajax({
        url: "/registerAddMsg/addMsg",
        type: 'POST',
        async: false,
        data: {"accountId": vm.register_MerchantInformation_id,"companyName":companyName,"contactsName":contactsName,"contactsMobile":contactsMobile,"email":email,"accountToken":sessionStorage.getItem("accountToken")},
        dataType: "json",
        error: function () {
            layer.msg("添加企业信息异常");
        },
        success: function (data) {
            if(data == 0){
                layer.msg("添加企业信息失败，请重试");
            }else {
                sessionStorage.removeItem("register_MerchantInformation_id");
                sessionStorage.removeItem("register_MerchantInformation_mobile");
                window.location.href = "/registerAddMsg/registerSuccess";
            }
        }
    });
}