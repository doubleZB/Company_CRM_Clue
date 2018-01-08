var vm=new Vue({
	el:"#content",
	data:{
		accountToken:"",
		balance:"0",//余额
		credit:"0",//授信余额
		loan:"0",//欠款
		userCount:"0",//员工人数
		companyName:"",//公司名称
		companyNumber:"",//公司ID
		yessterday:"0",//昨日消费金额
		authenticationStatus:0,//企业认证状态 0：未认证 1：认证成功 2:认证失败 3:审核中
		json:[
			{status:"1",src:"/static/images/product_1.png",Txt:"为广大客户提供完整的短信流量解决方案，包括：短信验证码、短信通知、国际短信、语音验证、呼叫中心、流量营销、话费营销。"},
			{status:"2",src:"/static/images/product_2.png",Txt:"免费的个人客户管理工具，整合电子名片、日历、联络提醒、生日提醒、企业信息查询、客户地图 ，庞大功能体系帮助商务人士管理客户以及人脉资源。"},
			{status:"2",src:"/static/images/product_3.png",Txt:"适用于互联网／传统项目管理，任务分发，更精准的计算出项目的成本核算。"},
		]
		//status  判断是否开通了该产品     1==开通     2==没开通
		//src  图片路径
	},
	methods:{
		add:function(){
			$(".select").show();
		},
		selectA:function(){
			$(".select").hide();
		},
		addDepartment:function(){
			window.location.href = "/organizational/skipOrganizational";
		},
		addUser:function(){
			window.location.href = "/organizational/skipOrganizational";
		},
		recharge:function(){
			window.location.href = "/bill/billQuery";
		},
		details:function(){
			sessionStorage.setItem("homePage_BillDetails_details","yesterday");
			window.location.href = "/bill/billDetails";
		},
		authentication:function(){
			window.location.href = "/company/EnterpriseCertification";
		}
	}
})
$(function(){
	vm.accountToken = sessionStorage.getItem("accountToken");
	if(sessionStorage.getItem("accountToken") == null || sessionStorage.getItem("accountToken") == ""){
		var win = window;
		while (win != win.top) {
			win = win.top;
		}
		win.location.href = "/login/loginFail"
	}
	//显示主页信息
	$.ajax({
		url: "/indexPageShow/indexShow",
		type: 'POST',
		async: false,
		data: {"accountToken":vm.accountToken},
		dataType: "json",
		error: function () {
			layer.msg("加载首页异常");
		},
		success: function (data) {
			if(data.data.company.companyLogo != null && data.data.company.companyLogo != ""){
				$("#companyLogo").attr("src",data.data.company.companyLogo);
			}
			vm.authenticationStatus = data.data.company.authenticationStatus;
			vm.companyName = data.data.company.companyName;
			vm.companyNumber = data.data.company.companyNumber;
			vm.userCount = data.data.userCount;
			vm.credit = data.data.wallet.credit;
			vm.loan = data.data.wallet.loan;
			vm.balance = data.data.wallet.balance;
			vm.yessterday = data.data.allAmountMoney;
		}
	});

})