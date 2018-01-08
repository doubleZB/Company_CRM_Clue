layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#accountTime'
    });
})
var vm=new Vue({
    el:"#content",
    data:{
        accountToken:"",
        balance:"0",//余额
        credit:"0",//授信余额
        loan:"0",//欠款
        bankPicture:"",//银行回单截图
    },
    methods:{
    }
})
$(function(){
    vm.accountToken = sessionStorage.getItem("accountToken");
    if(sessionStorage.getItem("accountToken") == null || sessionStorage.getItem("accountToken") == ""){
        parent.window.location.href = "/login/loginFail"
    }
    //显示主页信息
    $.ajax({
        url: "/bill/walletDetail",
        type: 'POST',
        async: false,
        data: {"accountToken":vm.accountToken},
        dataType: "json",
        error: function () {
            layer.msg('加载钱包数据异常');
        },
        success: function (data) {
            vm.balance = data.balance;
            vm.credit = data.credit;
            vm.loan = data.loan;
        }
    });

})
function submitAccount(){
    if($("#accountName").val() == null || $("#accountName").val() == ""){
        layer.msg("银行户名不能为空");
        return false;
    }
    if($("#accountBank").val() == null || $("#accountBank").val() == ""){
        layer.msg("入账银行不能为空");
        return false;
    }
    if($("#accountAmount").val() == null || $("#accountAmount").val() == ""){
        layer.msg("转账金额不能为空");
        return false;
    }
    if($("#accountTime").val() == null || $("#accountTime").val() == ""){
        layer.msg("转账时间不能为空");
        return false;
    }
    alert($("#accountTime").val());
    $("#bankPicture").upload({
        url: '/bill/upload',
        params: {},
        dataType: 'json',
        onSend: function (obj, str) {
            //应该是上传加载圆圈
            index = layer.load(1, {shade: [0.1, '#fff']});
            return true;
        },
        onComplate: function (data) {
            //关闭加载圆圈
            layer.close(index);
            $("#img_id").attr("src",data.reurl);
            var bankPictureUrl = data.reurl;
            var accountName = $("#accountName").val();
            var accountBank = $("#accountBank").val();
            var accountAmount = $("#accountAmount").val();
            var accountTime = $("#accountTime").val();
            alert("充值数据-bankPictureUrl : 【"+bankPictureUrl+"】, accountName : 【"+accountName+"】 , accountBank : 【"+accountBank+
                "】 , accountAmount : 【"+accountAmount+"】 , accountTime : 【"+accountTime+"】.");
        }
    });
    //上传？
    $("#bankPicture").upload("ajaxSubmit");
}
$('#bankPicture').change(function (event) {
    var files = event.target.files;
    if (files && files.length > 0) {
        file = files[0];
        var URL = window.URL || window.webkitURL;
        var imgURL = URL.createObjectURL(file);
        $("#img_id").attr("src",imgURL);
    }
});