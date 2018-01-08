var vm=new Vue({
	el:"#content",
	data:{
		LoginPassword:1,//登录密码
		PaymentPassword:2,//支付密码
        remind:2,//余额不足提醒
        accountToken:sessionStorage.getItem("accountToken"),
        loginPasswordShow:"******",
        paymentPasswordShow:"暂未设置",
        remindShow:"暂未设置",
        balanceLower:0,
        mobile:"",
        remindId:"",
        companyId:"",
        status:""
	},
    created: function(){
	    this.getAccountSetInfo();
    },
	methods:{
		downAll:function(){
			$(".position").hide();
			$(".zhe").hide();
            $("#password").val("");
            $("#newPwdOne").val("");
            $("#newPwdTwo").val("");
            $("#payPwd").val("");
            $("#payNewPwdOne").val("");
            $("#payNewPwdTwo").val("");
            $("#payPwdOne").val("");
            $("#payPwdTwo").val("");
		},
		LoginPasswordBtn:function(num){//修改密码
			//num==1   修改密码    num==2  保存
			if(num==1){
				$(".LoginPassword").show();
				$(".zhe").show();
			}else if(num==2){
				
			}
		},
		PaymentPasswordBtn:function(num){//修改支付密码
			//num==1   设置支付密码    num==2  修改支付密码    num==3  保存
			if(num==1){
				$(".PaymentPassword").show();
				$(".zhe").show();
			}else if(num==2){
				$(".editPassword").show();
				$(".zhe").show();
			}else{
				
			}
		},
		tipTop:function(num){//设置提醒
			//num==1   设置提醒       num==3  保存
			if(num==1){
				$(".tipTop").show();
				$(".zhe").show();
				this.getAccountSetInfo();
			}else{
			}
		},
        getAccountSetInfo:function () {
		    var that = this;
            $.ajax({
                url: "/accountSet/getAccountSetInfo",
                type: 'POST',
                async: false,
                data: {"accountToken": that.accountToken},
                dataType: "json",
                traditional: true,
                error: function () {
                    layer.msg("获取账户设置信息失败");
                },
                success: function (data) {
                    if (data.wallet.password != '' && data.wallet.password != null){
                        that.PaymentPassword = 1;
                        that.paymentPasswordShow = "******";
                    }
                    if (data.remind != null){
                        that.remind = 1;
                        that.balanceLower = data.remind.balanceLower;
                        that.mobile = data.remind.mobile;
                        that.remindId = data.remind.id;
                        that.status = data.remind.status;
                        $("#status").val(that.status);
                    }
                    that.companyId = data.company.id;
                }
            });
        },
        //提交修改的新密码
        submitNewPwd:function () {
            var reg = /^(?=.*\d)(?=.*[a-zA-Z]).{4,10}$/;
            if ($("#password").val() == ""){
                layer.msg("原密码不能为空");
                return false;
            }
            if ($("#newPwdOne").val() == ""){
                layer.msg("新密码不能为空");
                return false;
            }
            if (!reg.test($("#newPwdOne").val())) {
                layer.msg("密码不符合规则，请重新输入！");
                return false;
            }
            if ($("#newPwdTwo").val() == ""){
                layer.msg("确认新密码不能为空");
                return false;
            }
            if ($("#newPwdOne").val() != $("#newPwdTwo").val()){
                layer.msg("两次密码不一致");
                return false;
            }
            this.checkAndUpdatePwd($("#password").val(),$("#newPwdOne").val())
        },
        //验证原密码是否正确，如果正确进行密码更新
        checkAndUpdatePwd:function (pwd,newPwd) {
            var that = this;
            $.ajax({
                url: "/accountSet/checkAndUpdatePwd",
                type: 'POST',
                async: false,
                data: {"accountToken": that.accountToken,"pwd":pwd,"newPwd":newPwd},
                dataType: "json",
                traditional: true,
                error: function () {
                    layer.msg("检查原密码失败");
                },
                success: function (data) {
                    if (data.status){
                        layer.msg(data.msg,{time:2000},function () {
                            that.downAll();
                        });
                    }else {
                        layer.msg(data.msg);
                    }
                }
            });
        },
        //提交修改的新密码
        submitPayNewPwd:function (type) {
            var reg = /^(?=.*\d)(?=.*[a-zA-Z]).{4,10}$/;
		    if (type==1){
                if ($("#payPwd").val() == ""){
                    layer.msg("原支付密码不能为空");
                    return false;
                }
                if ($("#payNewPwdOne").val() == ""){
                    layer.msg("新支付密码不能为空");
                    return false;
                }
                if (!reg.test($("#payNewPwdOne").val())) {
                    layer.msg("密码不符合规则，请重新输入！");
                    return false;
                }
                if ($("#payNewPwdTwo").val() == ""){
                    layer.msg("确认新支付密码不能为空");
                    return false;
                }
                if ($("#payNewPwdOne").val() != $("#payNewPwdTwo").val()){
                    layer.msg("两次密码不一致");
                    return false;
                }
                this.checkAndUpdatePayPwd($("#payPwd").val(),$("#payPwdOne").val());
            }else {
                if ($("#payPwdOne").val() == ""){
                    layer.msg("支付密码不能为空");
                    return false;
                }
                if (!reg.test($("#payPwdOne").val())) {
                    layer.msg("密码不符合规则，请重新输入！");
                    return false;
                }
                if ($("#payPwdTwo").val() == ""){
                    layer.msg("确认支付密码不能为空");
                    return false;
                }
                if ($("#payPwdOne").val() != $("#payPwdTwo").val()){
                    layer.msg("两次密码不一致");
                    return false;
                }
                this.checkAndUpdatePayPwd("",$("#payPwdOne").val());
            }

        },
        //验证原密码是否正确，如果正确进行密码更新
        checkAndUpdatePayPwd:function (pwd,newPwd) {
            var that = this;
            $.ajax({
                url: "/accountSet/checkAndUpdatePayPwd",
                type: 'POST',
                async: false,
                data: {"accountToken": that.accountToken,"pwd":pwd,"newPwd":newPwd},
                dataType: "json",
                traditional: true,
                error: function () {
                    layer.msg("检查原密码失败");
                },
                success: function (data) {
                    if (data.status){
                        layer.msg(data.msg,{time:2000},function () {
                            that.downAll();
                            that.getAccountSetInfo();
                        });
                    }else {
                        layer.msg(data.msg);
                    }
                }
            });
        },
        //插入或者更新余额提醒
        insertOrUpdateRemind:function (){
		    var that = this;
            if ($("#balanceLower").val()==''){
                layer.msg("余额提醒不能为空");
                return false;
            }
            if ($("#mobile").val()==''){
                layer.msg("手机号不能为空");
                return false;
            }
            var remindId = $("#remindId").val();
            var status = $("#status option:selected").val();
            var balanceLower = $("#balanceLower").val();
            var mobile = $("#mobile").val();
            var companyId = $("#companyId").val();
            $.ajax({
                url: "/accountSet/insertOrUpdateRemind",
                type: 'POST',
                async: false,
                data: {"remindId": remindId,"status":status,"balanceLower":balanceLower,"mobile":mobile,"companyId":companyId},
                dataType: "json",
                traditional: true,
                error: function () {
                    layer.msg("余额提醒设置失败");
                },
                success: function (data) {
                    if (data){
                        layer.msg("余额提醒设置成功",{time:2000},function () {
                            that.downAll();
                            that.getAccountSetInfo();
                        });
                    }else {
                        layer.msg("余额提醒设置失败");
                    }
                }
            });
        }
	}
});