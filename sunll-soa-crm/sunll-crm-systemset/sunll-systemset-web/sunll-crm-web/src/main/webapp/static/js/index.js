var vm=new Vue({
	el:"#content",
	data:{
		UserName:"",//用户名
		notice:"0",//消息通知
		HeadPortrait:"HeadPortrait.png",//用户头像
		activeClass:"fa fa-",
		accountToken:"",
		registerSuccess_index_status:"",
		loginTimeShow:"",
		equipment:"",
		loginAddress:"",
		//status_nav判断是否有二级菜单1=没有2=有  name菜单名称
		// secondMenuList二级菜单名称  contUrliframe跳转路径
		json:[
			{name:"首页",status_nav:1,contUrl:"/indexPageShow/home",secondMenuList:[
			],iconFont:"home",},
			{name:"账户",status_nav:2,contUrl:"",secondMenuList:[
				{name:"商户信息",contUrl:"/company/companyInfo"},
				{name:"企业认证",contUrl:"/company/EnterpriseCertification"},
				{name:"账户设置",contUrl:"/accountSet/skipAccountSet"},
				{name:"组织架构",contUrl:"/organizational/skipOrganizational"},
			],iconFont:"address-card-o",},
			{name:"财务",status_nav:2,contUrl:"",secondMenuList:[
				{name:"账单查询",contUrl:"/bill/billDetails"},
				{name:"账户充值",contUrl:"/bill/billQuery"},
			],iconFont:"cny",},
			//{name:"应用",status_nav:1,contUrl:"",secondMenuList:[
			//],iconFont:"th-large",},
			//{name:"营销",status_nav:2,contUrl:"",secondMenuList:[
			//	{name:"营销活动",contUrl:""},
			//],iconFont:"line-chart",},
			//{name:"代理",status_nav:1,contUrl:"",secondMenuList:[
			//],iconFont:"handshake-o",}
		],
	},
	ready:function(){
		$(".menu").eq(0).addClass("active");
	},
	methods:{
		body:function(e){
			e.stopPropagation();
			$(".showListMenu").hide();
		},
		showList:function(e){
			e.stopPropagation();
			$(".showListMenu").show();
		},
		stopP:function(e){
			e.stopPropagation();
		},
		//上次登录时间
		upTime:function(){
			layer.open({
				type: 1,
				title: "上次登录时间",
				area: ['360px', '260px'],
				content: $(".upTime"),
				anim: 1,
				btn: ['知道了'],
			})
		},
		//上次登录时间
		loginout:function(){
			sessionStorage.removeItem("accountToken");
			window.location.href = "/login/loginOut";
		},
		iframeHref:function(_url){//menu  跳转
			$("#iframe").attr("src",_url);
		},
		slideClick:function(e,i,num,_url){//二级菜单
			$(".menu").removeClass("active");
			$(".menu").eq(i).addClass("active");
			$(e.target).parents(".navItem").siblings().find(".menuList").slideUp();
			if(num==0){
				$("#iframe").attr("src",_url);
			}else{
				$(e.target).parents(".navItem").find(".menuList").slideToggle();
			}
		}
	}
})
$(function (){
	vm.accountToken = sessionStorage.getItem("accountToken");
	vm.registerSuccess_index_status = sessionStorage.getItem("registerSuccess_index_status");
	if(sessionStorage.getItem("accountToken") == null || sessionStorage.getItem("accountToken") == ""){
		var win = window;
		while (win != win.top) {
			win = win.top;
		}
		win.location.href = "/login/loginFail"
	}
	//注册跳转1主页2组织架构  0登录主页
	if(vm.registerSuccess_index_status == "2"){
		sessionStorage.setItem("registerSuccess_index_status", "0");
		$("#iframe").attr("src","/organizational/skipOrganizational");
		$.ajax({
			url: "/recoedTime/recoedTime",
			type: 'POST',
			async: false,
			data: {"accountToken":vm.accountToken},
			dataType: "json",
			error: function () {
			},
			success: function (data) {
				vm.UserName = data.data.loginAccount.accountNumber;
				vm.equipment = data.data.loginRecordTime.equipment;
				vm.loginTimeShow = data.data.loginRecordTime.loginTimeShow;
			}
		});
	}else {
		$.ajax({
			url: "/recoedTime/recoedTime",
			type: 'POST',
			async: false,
			data: {"accountToken":vm.accountToken},
			dataType: "json",
			error: function () {
			},
			success: function (data) {
				vm.UserName = data.data.loginAccount.accountNumber;
				vm.equipment = data.data.loginRecordTime.equipment;
				vm.loginAddress = data.data.loginRecordTime.loginAddress;
				vm.loginTimeShow = data.data.loginRecordTime.loginTimeShow;
			}
		});
	}
})