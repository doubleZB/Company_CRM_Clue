$.ajaxSetup({
    //页面存在accountToken而data中加上
    beforeSend: function() {
        //accountToken存在
        if(sessionStorage.getItem("USER_TOKEN") == null || sessionStorage.getItem("USER_TOKEN") == ""){
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            win.location.href = "/jspSkip/loginOut";
        }
    },
    complete: function () {
        if(sessionStorage.getItem("USER_TOKEN") == null || sessionStorage.getItem("USER_TOKEN") == ""){
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            win.location.href = "/jspSkip/loginOut";
        }
    }
});
$(function(){
    if(sessionStorage.getItem("USER_TOKEN") == null || sessionStorage.getItem("USER_TOKEN") == ""){
        var win = window;
        while (win != win.top) {
            win = win.top;
        }
        win.location.href = "/jspSkip/loginOut";
    }
});