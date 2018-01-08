<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人脉旺</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/index.css" rel="stylesheet">
    <style>
        .nav > li > a {
            font-weight: normal;
        }
        .closeBtn {
            font-size: 16px;
            float: right;
            margin-top: -45px;
            margin-right: 17px;
            opacity: .7;
        }

    </style>
</head>
<body>
<div id="wrapper">
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
                <%--<li>--%>
                <%--<a href="/jspSkip/index">--%>
                <%--<i class="fa fa-th-large"></i>--%>
                <%--<span class="nav-label">工作台</span>--%>
                <%--</a>--%>
                <%--</li>--%>
            </ul>
            <!-- 菜单循环 -->
            <%--<ul class="nav metismenu" id="side-menu">--%>
            <%--<li v-for="item in navList" class="{{ active }}" v-if="item.menuStatus==1"><!-- menuStatus：1前台2下拉3后台 menuLevel：1一级菜单2二级菜单 -->--%>
            <%--<a @click="iframeHref(item.url,$event)" class="hrefColor">--%>
            <%--<i class="fa {{ item.imgFlag }}"></i>--%>
            <%--<span class="nav-label">{{ item.name }}</span>--%>
            <%--<span v-if="item.permissionsList.length > 0" class="fa arrow"></span>--%>
            <%--</a>--%>
            <%--<ul v-if="item.permissionsList.length > 0" class="nav nav-second-level">--%>
            <%--<li v-for="items in item.permissionsList" class="{{ active }}" v-if="items.menuLevel==2"><!-- menuStatus：1前台2下拉3后台 menuLevel：1一级菜单2二级菜单 -->--%>
            <%--<a @click="iframeHref(items.url,$event)" class="hrefColor">{{ items.name}}</a>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--</ul>--%>

            <ul class="nav metismenu" id="side-menu">
                <li v-for="item in navList" v-if="item.menuStatus==1" class="menuList">

                    <a @click="iframeHref(item.url,$event)" class="hrefColor">
                        <i class="fa  {{ item.imgFlag }}"></i>
                        <span class="nav-label">{{ item.name }}</span>
                        <span v-if="item.permissionsList.length > 0" class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse" v-if="item.permissionsList.length > 0" status="1">
                        <li v-for="items in item.permissionsList" v-if="items.menuLevel==2"
                            class="{{ items.contUrl == activeUrl ? 'active' : ''  }}">
                            <a @click="iframeHref(items.url,$event)"  class="hrefColor">{{ items.name}}</a>
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
                    <%--<li class="relative">--%>
                        <%--<input type="text" placeholder="请输入关键词" class="input-sm form-control"--%>
                               <%--name="top-search" id="top-search">--%>
                        <%--<i class="fa fa-search"></i>--%>
                    <%--</li>--%>
                    <li class="dropdown" @click="queryMms()">
                        <a class="right-sidebar-toggle count-info">
                            <i class="fa fa-envelope-o font20"></i> <span class="label badge badge-warning ">{{smsNumber}}</span>
                        </a>
                    </li>
                    <li class="" onMouseOver="displaySubMenu('fg')" onMouseOut="hideSubMenu('fg')">
                        <a class="dropdown-toggle count-info" href="#">
                            <img src="${pageContext.request.contextPath}/static/src/images/profile_small.jpg"
                                 class="img-circle" alt="">
                            {{loginUserName}}
                            <%--<b class="caret"></b>--%>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts" id="fg">
                            <li>
                                <a @click="lastLoginTime()">
                                    <i class="divider"></i>
                                    <i class="fa fa-cog"></i> 上次登录时间
                                </a>
                            </li>
                            <li v-for="item in navList" v-if="item.menuStatus==3">
                                <!-- menuStatus：1前台2下拉3后台 menuLevel：1一级菜单2二级菜单 -->
                                <a href="{{item.url}}">
                                    <i class="divider"></i>
                                    <i class="fa fa-cog"></i> {{item.name}}
                                </a>
                            </li>
                            <li>
                                <a @click="iframeHrefPerson('/jspSkip/user')">
                                    <i class="divider"></i>
                                    <i class="fa fa-power-off"></i> 个人中心
                                </a>
                            </li>
                            <li>
                                <a @click="loginOut()">
                                    <i class="divider"></i>
                                    <i class="fa fa-power-off"></i> 退出系统
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div id="right-sidebar" class="animated popUp" style="overflow: auto;cursor:pointer;">
            <h4>消息</h4>
            <span class="closeBtn" onclick="close()">X</span>
            <div class="col-lg-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>提醒</h5>
                        <div class="ibox-tools">
                            <a href="###">
                                {{smsNumber}}
                            </a>
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>

                        </div>
                    </div>
                    <div class="ibox-content ibox-heading">
                        <small><i class="fa fa-tim"></i> 您有{{smsAllNumber}}条消息,{{smsNumber}}条未读.</small>
                    </div>
                    <div class="ibox-content">
                        <div class="feed-activity-list">
                            <div class="feed-element" v-for="item in smsList" v-if="item.showStatus==1">
                                <div @click="updateMmsOne(item.id,item.specificId,item.typeId)">
                                    <strong>{{item.smsText}}</strong>
                                    <small class="text-muted">{{item.smsDateShow}}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ibox-content ibox-heading">
                        <small><i class="fa fa-tim"></i> 已读消息</small>
                    </div>
                    <div class="ibox-content">
                        <div class="feed-activity-list">
                            <div class="feed-element" v-for="item in smsList" v-if="item.showStatus==2" style="cursor:pointer;">
                                <div @click="skipClue(item.id,item.specificId,item.typeId)">
                                    <strong>{{item.smsText}}</strong>
                                    <small class="text-muted">{{item.smsDateShow}}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="iframe-wrap" class="iframe-wrap animated fadeInRight">
            <iframe src="/jspSkip/clue" frameborder="0" id="iframe" class="animated fadeInRight"></iframe>
        </div>
        <!--  消息弹框 -->
        <div id="winpop" style="height: 204px; display: none;" class="text-center">
            <div class="title">消息提醒<span class="winpop_close" onclick="hide1()"><B>X</B></span></div>
            <div class="border-bottom"></div>
            <div class="con">{{oneMessageShow}}</div>
            <button type="button" class="btn btn-w-m btn-primary" @click="updateMmsOneShow()">查看消息</button>
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
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/js/ajax.session.time.out.js"></script>
<script>
    $(".closeBtn").click(function(){
        $(".popUp").hide();
    });
    $(".dropdown").click(function(){
        $(".popUp").show();
    });

    function show1() {
        $("#winpop").css("bottom", 0);
    }
    function hide1() {
        $("#winpop").css("bottom", "-220px");
    }
    var vm = new Vue({
        el: "#wrapper",
        data: {
            loginUserName: "",
            smsNumber: 0,
            smsAllNumber: 0,
            smsList: [],
            navList: [],
            oneMessageShow: "",
            messageId: 0,
            identifyId: "",
            businessTypeId: 0
        },
        methods: {
            //直接跳转
            skipClue: function (mmsId, specificId, typeId) {
                //校验唯一标识是否存在
                $.ajax({
                    url: "/sms/checkIdentify",
                    type: 'POST',
                    async: false,
                    data: {"identifyId": specificId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            $("#iframe").attr("src", "/clue/skipDetail?clueId=" + specificId + "&businessTypeId=" + typeId);
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                });
            },
            updateMmsOneShow: function () {
                $.ajax({
                    url: "/sms/updateMmsOne",
                    type: 'POST',
                    async: false,
                    data: {"mmsId": vm.messageId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (dataReturn) {
                    }
                });
                //校验唯一标识是否存在
                $.ajax({
                    url: "/sms/checkIdentify",
                    type: 'POST',
                    async: false,
                    data: {"identifyId": vm.identifyId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            hide1();
                            $("#iframe").attr("src", "/clue/skipDetail?clueId=" + vm.identifyId + "&businessTypeId=" + vm.businessTypeId);
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                });
            },
            //更改某条消息的状态
            updateMmsOne: function (mmsId, specificId, typeId) {
                $.ajax({
                    url: "/sms/updateMmsOne",
                    type: 'POST',
                    async: false,
                    data: {"mmsId": mmsId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (dataReturn) {
                        vm.queryMms();
                    }
                });
                //校验唯一标识是否存在
                $.ajax({
                    url: "/sms/checkIdentify",
                    type: 'POST',
                    async: false,
                    data: {"identifyId": specificId},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            $("#iframe").attr("src", "/clue/skipDetail?clueId=" + specificId + "&businessTypeId=" + typeId);
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                });
            },
            //查看消息，并获取最新的消息列表
            queryMms: function () {
                //获取消息
                $.ajax({
                    url: "/sms/selectAllSmsList",
                    type: 'POST',
                    async: false,
                    data: {"userId": parseInt(sessionStorage.getItem("USER_TOKEN"))},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data1) {
                        if (data1.data != null && data1.data != "") {
                            vm.smsList = data1.data;
                            vm.smsAllNumber = vm.smsList.length;
                            var aa = 0;
                            for (var i = 0; i < data1.data.length; i++) {
                                var veh = data1.data[i];
                                if (veh.showStatus == "1") {
                                    aa += 1;
                                }
                            }
                            vm.smsNumber = aa;
                        }
                    }
                });
            },
            iframeHref: function (url, e) {
                if (url) {
                    $(".hrefColor").css("background", "#37445f")
                    $(e.target).parent("li").find("a").css("background", "#2885ec");
                    if ($(e.target).parent("li").length) {
                        $(e.target).parent("li").find("a").css("background", "#2885ec");
                    } else {
                        $(e.target).parents("li").find("a").css("background", "#2885ec");
                    }
                    $("#iframe").attr("src", url);
                }else{

                    if($(e.target).parents(".menuList").find("ul").attr("status")==1){
                        $(e.target).parents(".menuList").find("ul").attr("status",2);
                        $(e.target).parents(".menuList").find("ul").slideDown();
                    }else{
                        $(e.target).parents(".menuList").find("ul").attr("status",1);
                        $(e.target).parents(".menuList").find("ul").slideUp();
                    }
                }
            },
            iframeHrefPerson: function (url) {
                if (url) {
                    $("#iframe").attr("src", url);
                }
            },
            userCenter: function () {
                vm.iframeHref("/jspSkip/user");
            },
            //上次登录时间
            lastLoginTime: function () {
                $.ajax({
                    url: "/menuShow/lastLoginTime",
                    type: 'POST',
                    async: false,
                    data: {"CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN")},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            layer.msg("上次登录时间：" + data.data, {time: 3000})
                        }
                    }
                });
            },
            //退出
            loginOut: function () {
                $.ajax({
                    url: "/login/loginOut",
                    type: 'POST',
                    async: false,
                    data: {"CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN")},
                    dataType: "json",
                    error: function () {
                    },
                    success: function (data) {
                        if (data.status == 200) {
                            sessionStorage.removeItem("USER_TOKEN");
                            sessionStorage.removeItem("CRM_TOKEN");
                            sessionStorage.clear();
                            window.location.href = "/jspSkip/loginOut";
                        }
                    }
                });
            },
        }
    });
    //获取菜单
    $(function () {
        $.ajax({
            url: "/menuShow/menuShow",
            type: 'POST',
            async: false,
            data: {"CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN")},
            dataType: "json",
            error: function () {
            },
            success: function (data) {
                vm.navList = data;
                vm.queryMms();
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
                sessionStorage.setItem("USER_TELEPHONE", data.telephone);//分机号
                sessionStorage.setItem("USER_MOBILE", data.mobile);//主机号
            }
        });
    });
    //弹框
    $(function () {
        window.setInterval(function () {
            $.ajax({
                url: "/sms/selectSmsList",
                type: 'POST',
                async: false,
                data: {"userId": parseInt(sessionStorage.getItem("USER_TOKEN"))},
                dataType: "json",
                error: function () {
                },
                success: function (data) {
                    if (data.data != null && data.data != "") {
                        if (data.data.length != 0) {
                            vm.oneMessageShow = "" + data.data[0].smsText + ",跟进时间:" + data.data[0].smsDateShow + ".";
                            vm.identifyId = data.data[0].specificId;
                            vm.messageId = data.data[0].id;
                            vm.businessTypeId = data.data[0].typeId;
                            $("#winpop").show();
                            show1();
                        }
                    }
                }
            });
        }, 180000)
//        }, 10000)
    });
    //消息数目
    $(function () {
        window.setInterval(function () {
            vm.queryMms();
        }, 20000)
    })


    function displaySubMenu(dropdown) {
        var subMenu = document.getElementById(dropdown);
        subMenu.style.display = "block";
    }
    function hideSubMenu(dropdown) {
        var subMenu = document.getElementById(dropdown);
        subMenu.style.display = "none";
    }
</script>
</html>