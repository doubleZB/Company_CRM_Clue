window.onload = function(){
    if(navigator.userAgent.indexOf("MSIE")>0){
        if(navigator.userAgent.indexOf("MSIE 6.0")>0){
            document.getElementsByTagName("body")[0].innerHTML = "<h1 style='text-align:center;margin:100px auto;background:#ff0000;font-size:30px; '>浏览器版本过低，请更换高版本浏览器！</h1>";
        }
        if(navigator.userAgent.indexOf("MSIE 7.0")>0){
            document.getElementsByTagName("body")[0].innerHTML = "<h1 style='text-align:center;margin:100px auto;background:#f2f3f5;font-size:30px; '>浏览器版本过低，请更换高版本浏览器！</h1>";
        }
        if(navigator.userAgent.indexOf("MSIE 8.0")>0 && !window.innerWidth){//这里是重点，你懂的
            document.getElementsByTagName("body")[0].innerHTML = "<h1 style='text-align:center;margin:100px auto;background:#f2f3f5;font-size:30px; '>浏览器版本过低，请更换高版本浏览器！</h1>";
        }
    }
};
