<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人脉旺系统设置</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/index.css" rel="stylesheet">
    <style>
        .nav > li > a{
            font-weight: normal;
        }
        .navbar-default .nav > li > a:hover,.navbar-default .nav > li > a:focus{
            background-color: #2f3b53!important;
        }
    </style>

</head>
<body>
<div id="setUp">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <img alt="image" src="${pageContext.request.contextPath}/static/src/images/logo.png"/>
                    </div>
                    <div class="logo-element">
                        DXT
                    </div>
                </li>
            </ul>
            <ul class="nav metismenu" id="side-menu">
                <li v-for="item in navList" class="{{ active }}" v-if="item.menuStatus==2"><!-- menuStatus：1前台2下拉3后台 menuLevel：1一级菜单2二级菜单 -->
                    <a @click="iframeHref(item.url)">
                        <i class="fa {{ item.imgFlag }}"></i>
                        <span class="nav-label">{{ item.name }}</span>
                        <span v-if="item.permissionsList.length > 0" class="fa arrow"></span>
                    </a>
                    <ul v-if="item.permissionsList.length > 0" class="nav nav-second-level">
                        <li v-for="items in item.permissionsList" class="{{ active }}" v-if="items.menuLevel==2"><!-- menuStatus：1前台2下拉3后台 menuLevel：1一级菜单2二级菜单 -->
                            <a @click="iframeHref(items.url,$event,1)" class="hrefColor">{{ items.name}}</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <div id="page-wrapper" class="gray-bg" style="min-height: 476px;">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <i class="navbar-minimalize minimalize-styl-2 title">CRM销售系统平台</i>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <button @click="backIndex" type="button" class="btn btn-warning  btn-sm">
                            返回首页
                        </button>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <img src="${pageContext.request.contextPath}/static/src/images/profile_small.jpg" class="img-circle" alt="">
                            {{loginUserName}}
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a @click="loginOut()">
                                    <i class="fa fa-power-off"></i> 退出系统
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>

            </nav>
        </div>
        <div id="iframe-wrap" class="iframe-wrap">
            <iframe src="/jspSkip/business" frameborder="0" id="iframe"></iframe>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/js/ajax.session.time.out.js"></script>
<script>
    var vm = new Vue({
        el: "#setUp",
        data: {
            navList: [],
            loginUserName:""
        },
        methods: {
            backIndex: function () {
                window.location.href = "/jspSkip/index"
            },
            iframeHref: function (url,e,num) {
                if (url) {
                    console.log(url);
                    $("#iframe").attr("src", url);
                    if(num==1){
                        $(".hrefColor").css({"color":"#a7b1c2","background":"#37445F"})
                        $(e.target).css({"color":"#fff","background":"#2885ec"});
                    }
                }else{
                    $("#side-menu li").eq(0).siblings().slideToggle();
                }
            },
            loginOut:function(){
                $.ajax({
                    url: "/login/loginOut",
                    type: 'POST',
                    async: false,
                    data: {},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        console.log(data)
                        if(data.status == 200){
                            sessionStorage.removeItem("USER_TOKEN");
                            sessionStorage.removeItem("CRM_TOKEN");
                            window.location.href="/jspSkip/loginOut";
                        }
                    }
                });
            },
        }
    })
    //获取菜单
    $(function(){
        $.ajax({
            url: "/menuShow/menuShow",
            type: 'POST',
            async: false,
            data: {"CRM_TOKEN":sessionStorage.getItem("CRM_TOKEN")},
            dataType: "json",
            error: function () {
            },
            success: function (data) {
                console.log(data)
                vm.navList=data;
            }
        });
        $.ajax({
            url: "/unitAccountInterface/getUserbyUserId",
            type: 'POST',
            async: false,
            data: {"userId": parseInt(sessionStorage.getItem("USER_TOKEN"))},
            dataType: "json",
            error: function () {
            },
            success: function (data) {
                vm.loginUserName = data.name;
            }
        });
    });

</script>
</html>