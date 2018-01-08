var vm=new Vue({
    el:"#content",
    data:{
        accountToken:"",
    }
})
$(function(){
    vm.accountToken = sessionStorage.getItem("accountToken");
})
//注册跳转1主页2组织架构
function submit_1(){
    sessionStorage.setItem("registerSuccess_index_status", "2");
    window.location.href = "/indexPageShow/index"
}
//注册跳转1主页2组织架构
function submit_2(){
    sessionStorage.setItem("registerSuccess_index_status", "1");
    window.location.href = "/indexPageShow/index"
}