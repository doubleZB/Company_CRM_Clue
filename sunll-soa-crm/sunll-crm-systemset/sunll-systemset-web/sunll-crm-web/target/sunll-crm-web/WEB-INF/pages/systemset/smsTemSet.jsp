<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>自定义短信模板</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/smsTemSet.css">
    <style>
        <%--改动字间距--%>
        [v-cloak] {
            display: none;
        }
        .header_top {
            height: 50px;
            line-height: 50px;
        }
        .table_box table tr th, .table_box table tr td {
            line-height: 50px;
            word-wrap: break-word;
            height: 100%;
        }



        .AddTape div{
            height:auto;
            clear: both;
        }
        body .font14{
            font-size: 14px!important;
            color:#333!important;
        }
        .cursor{
            cursor: pointer;
        }
        .table_box tr:nth-child(2n-1) {
            background: #fff;
        }
        .table_box table tr {
            background: #fff;
        }
    </style>
</head>
<body>
<div id="BusinessType">
    <div class="header_top">
        <span class="pull-left">自定义短信模板</span>
        <button type="button" @click="addOrEditBtn(1,'')" class="btn btn-w-m btn-primary pull-right">
            <i class="fa fa-plus-square-o"></i>新建模板
        </button>
    </div>
    <div class="table_box" style="margin-top:0;">
        <table >
            <thead>
            <tr class="trColor">
                <th class="font14">模板名称</th>
                <th class="font14">短信内容</th>
                <th class="font14">短信签名</th>
                <th class="font14">状态</th>
                <th class="font14">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in smsTemList">
                <td>{{item.name}}</td>
                <td>{{item.content}}</td>
                <td>{{item.signature}}</td>
                <td v-if="item.status == 1">审核通过</td>
                <td v-if="item.status == 2">审核中</td>
                <td v-if="item.status == 3">审核失败</td>
                <td>
                    <i class="fa fa-pencil-square-o" @click="addOrEditBtn(2,item.id)"> 编辑</i>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <!--显示隐藏-->
    <div class="AddTape">
        <h4>{{addOrEditTitle}}</h4>
        <div>
            <span class="pull-left">模板名称</span>
            <input type="hidden" name="smsTemId" id="smsTemId" />
            <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__" name="search" id="smsTemName"
                   placeholder="请输入业务类型,最长10个字符">
        </div>
        <div>
            <span class="pull-left">短信签名</span>
            <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__" name="search" id="smsTemSignature"
                   placeholder="请输入业务类型,最长10个字符">
        </div>
        <div>
            <span class="pull-left">短信内容</span>
            <textarea id="smsTemContent" style="width: 330px; height: 150px;"
                      placeholder="请输入短信内容，280字符内" rows="5" class="form-control input-sm __web-inspector-hide-shortcut__"></textarea>
        </div>
        <div>
            <span class="pull-left">测试手机</span>
            <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__" name="search" id="smsTemTest"
                   placeholder="请输入手机号，用于该条短信是否审核通过；">
        </div>
        <div class="posttio">
            <button type="button" class="btn btn-w-m btn-primary" @click="savleAddOrEditBtn">保存</button>
            <button type="button" class="btn btn-w-m btn-default" @click="cancelAddOrEditBtn">取消</button>
        </div>

    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/smsTemSet.js"></script>
</html>