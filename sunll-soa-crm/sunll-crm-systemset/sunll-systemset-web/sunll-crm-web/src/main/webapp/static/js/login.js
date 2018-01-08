$("input").on("focus",function(){
	$(this).siblings().css({color:"#fe641c"});
})
$("input").on("blur",function(){
	$(this).siblings().css({color:"#333"});
})
//注册
function register() {
	window.location.href = "/register/skipRegister";
}
//注册
function forgetPassword() {
	window.location.href = "/login/forgetPassword";
}
//登录
function login(){
	var accountNumber = $("#accountNumber").val();
	var password = $("#password").val();
	if (accountNumber == "") {
		layer.msg("请输入用户名");
		return false;
	}
	if (password == "") {
		layer.msg("请输入密码");
		return false;
	}
	if (accountNumber == "" && password == "") {
		layer.msg("用户名或密码不能为空")
		return false;
	}
	$.ajax({
		url: "/login/login",
		type: 'POST',
		async: false,
		data: {"accountNumber": accountNumber,"password":password},
		dataType: "json",
		error: function () {
			layer.msg("登录出错了")
		},
		success: function (data) {
			if(data.status == "200" && data.data != null){
				sessionStorage.setItem("accountToken", data.data);
				sessionStorage.setItem("registerSuccess_index_status", "0");
				window.location.href = "/indexPageShow/index";
				if($('#rememberAccount').is(':checked')) {
					setCookie("name",accountNumber);
					setCookie("password",password);
				}
			}else if(data.status == "500"){
				layer.msg(data.msg);
			}else {
				layer.msg("用户名或密码错误，请从新登录");
			}
		}
	});
}
function setCookie(name,value)
{
	var Days = 3;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1);
		if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
	}
	return "";
}
$(function(){
	if(getCookie("name") !=null && getCookie("name") !="" && getCookie("password") !=null && getCookie("password") != ""){
		$("#accountNumber").val(getCookie("name"));
		$("#password").val(getCookie("password"));
	}
})