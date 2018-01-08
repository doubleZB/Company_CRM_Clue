<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>人脉旺CRM登录,人脉旺CRM，营销平台CRM系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lgoinzzz/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lgoinzzz/amazeui.css">
    <![endif]-->
    <style>
        html,
        body {
            width: 100%;
            height: 100%;
            font-family: "微软雅黑";
            min-width: 1000px;
            min-height: 600px;
            background: url(${pageContext.request.contextPath}/static/lgoinzzz/z_rmwBg.jpg) no-repeat;
            background-size: 100% 100%;
        }

        * {
            margin: 0;
            padding: 0;
        }

        .container {
            margin: 0;
            padding: 0;
            width: inherit;
            min-width: 1000px;
            height: 100%;
            position: relative;
        }

        .icon {
            width: 104px;
            height: 104px;
            background: url(${pageContext.request.contextPath}/static/lgoinzzz/logoCrm4.png) no-repeat;
            background-size: 100% 100%;
            position: absolute;
            left: 50%;
            top: 30px;
            margin-left: -48px;
        }

        .z_content {
            position: absolute;
            width: 583px;
            height: 368px;
            background: #fff;
            left: 50%;
            top: 50%;
            margin-left: -284px;
            margin-top: -149px;
            color: #fff;
            border-radius: 12px;
        }

        .z_content h3 {
            text-align: center;
            margin: 20px 0;
            font-size: 28px;
            color: #1A78E3;
            font-weight: 100;
        }

        .copyRight {
            margin: 0 auto;
            color: #fff;
            font-size: 14px;
            position: absolute;
            bottom: 30px;
            left: 50%;
            margin-left: -240px;
        }

        .reminds {
            font-size: 14px;
            color: #4A4A4A;
            text-align: center;
            margin-bottom: 10px;
        }

        .companys {
            width: 100%;
            height: 200px;
            overflow: auto;
        }

        .companys ul {
            padding: 0;
        }

        .companys li {
            width: 390px;
            height: 60px;
            background: #fff;
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            line-height: 60px;
            text-align: center;
            font-size: 18px;
            color: #9B9B9B;
            margin: 0 auto 10px auto;
            cursor: pointer;
        }

        .companys li.active {
            background: #1A78E3;
            color: #FFFFFF;
            border-color: #1A78E3;
        }
    </style>

</head>

<body>

<div class="container" id="content" v-cloak>
    <div class="icon"></div>
    <div class="z_content">
        <h3>欢迎登录人脉旺CRM</h3>
        <div class="reminds">您的帐号属于多个公司，请选择要登录的公司</div>
        <div class="companys">
            <ul class="companysList">
                <div v-for="item in companyList">
                    <li id="{{item.id}}" @click="addClass($event,item.id,item.userId,item.telephone)">
                        {{item.companyName}}
                    </li>
                </div>
            </ul>
        </div>
    </div>
    <p class="copyRight">版权所有 ©北京聚通达科技股份有限公司未经许可不得复制、转载、违者必究！</p>
    <input id="userId" hidden name="userId" value="${userId}">
    <input id="companyId" hidden name="companyId" value="${companyId}">
    <input id="telephone" hidden name="telephone" value="${telephone}">
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lgoinzzz/jquery.min.js "></script>
<script src="${pageContext.request.contextPath}/static/lgoinzzz/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script src="${pageContext.request.contextPath}/static/lgoinzzz/common.js"></script>
<script type="text/javascript">
    $(function () {
        $(".companysList li").first().addClass("active");
    });
    $(function () {
        var userId = $("#userId").val();
        var companyId = $("#companyId").val();
        var telephone = $("#telephone").val();
        if (userId != null && userId != "" && companyId != null && companyId != "") {
            layer.msg("请稍等，选择企业中 . . . . . .",{time:3000},function(){
                $.ajax({
                    url: "/login/choseCompany",
                    type: 'POST',
                    async: false,
                    data: {"userId": userId.toString(), "companyId": companyId.toString()},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            sessionStorage.setItem("CRM_TOKEN", data.data);
                            sessionStorage.setItem("USER_TOKEN", userId);//用户id
                            sessionStorage.setItem("CRM_COMPANY_TOKEN", companyId);//企业id
                            sessionStorage.setItem("COMPANY_TELEPHONE", telephone);//主机号
                            console.log("主机号："+telephone);
                            console.log("用户id："+userId);
                            console.log("企业id："+companyId);
                            window.location.href = "/jspSkip/index";
                        } else {
                            layer.msg(data.msg,{time:3000},function(){
                                window.location.href = "/jspSkip/loginOut";
                            });
                        }
                    }
                });
            });

        } else {
            $.ajax({
                url: "/login/getCompanyListByAccountId",
                type: 'POST',
                async: false,
                data: {"accountId": parseInt(sessionStorage.getItem("ACCOUNT_ID_TOKEN"))},
                dataType: "json",
                error: function () {
                },
                success: function (data) {
                    console.log(data);
                    if (data.status == 200) {
                        vm.companyList = data.data;
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    });

    var vm = new Vue({
        el: "#content",
        data: {
            companyList: []
        },
        methods: {
            addClass: function (e, companyId, userId, telephone) {
                $(e.target).addClass("active").siblings().removeClass("active");
                $.ajax({
                    url: "/login/choseCompany",
                    type: 'POST',
                    async: false,
                    data: {"userId": userId, "companyId": companyId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        console.log(data);
                        if (data.status == 200) {
                            sessionStorage.setItem("CRM_TOKEN", data.data);
                            sessionStorage.setItem("USER_TOKEN", userId);//用户id
                            sessionStorage.setItem("CRM_COMPANY_TOKEN", companyId);//企业id
                            sessionStorage.setItem("COMPANY_TELEPHONE", telephone);//主机号
                            window.location.href = "/jspSkip/index";
                        } else {
                            alert(data.msg);
                            window.location.href = "/jspSkip/loginOut";
                        }
                    }
                });
            }
        }
    });

</script>
</html>
