<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>选择添加部分/成员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/zTreeStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/reset.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/director.css"/>
    <style>
        .contactList li a {
            line-height: 22px;
            font-size: 12px!important;
        }
    </style>
</head>
<body id="content">
<div class="wrap">
    <p class="Title">请选择要添加的部门/成员</p>
    <div class="main">
        <div class="mainLeft">
            <p><i class="fa fa-search"></i><input type="text" placeholder="请输入部门名称查询"id="userName" class="search"/></p>
            <div class="selectAll">
                <p @click="searchCont($event,item.id,item.name)" v-for="item in user">{{item.name}}</p>
            </div>
            <div class="mainCont">
                <div v-for="item in peopleNum">
                    {{item.name}}<span @click="removeList($index,item.id)">×</span>
                </div>
            </div>
        </div>
        <div class="mainRight">
            <p>请选择该部门（单选）</p>
            <div class="z_container">
                <div class="nav">
                    <ol class="am-breadcrumb marginB0">
                        <li class="am-active" id="d{{departmentId}}">{{departmentName}}</li>
                    </ol>
                </div>
                <ul class="contactList department">

                </ul>
                <ul class="contactList person marginT20">

                </ul>
                <div class="remark" style="display:none;">暂无数据</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.ztree.core.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.ztree.excheck.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.ztree.exedit.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/director.js"></script>
