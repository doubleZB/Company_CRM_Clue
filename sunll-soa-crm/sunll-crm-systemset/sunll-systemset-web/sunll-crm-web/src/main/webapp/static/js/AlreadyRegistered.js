var vm=new Vue({
	el:"#content",
	data:{
		register_alreadyRegistered_mobile:"",
		json:[
			{url:"images/sou.png",name:""}
		]
	}
})
$(function(){
	vm.register_alreadyRegistered_mobile = sessionStorage.getItem("register_alreadyRegistered_mobile");
	$.ajax({
		url: "/register/getAccountByMible",
		type: 'POST',
		async: false,
		data: {"mobile": vm.register_alreadyRegistered_mobile},
		dataType: "json",
		error: function () {
			layer.msg("查询账号异常")
		},
		success: function (data) {
			console.log(data)
			vm.json[0].name = data.accountNumber;
		}
	});
})
function Submit(){
	sessionStorage.removeItem("register_alreadyRegistered_mobile");
	window.location.href = "/login/loginIndex";
}