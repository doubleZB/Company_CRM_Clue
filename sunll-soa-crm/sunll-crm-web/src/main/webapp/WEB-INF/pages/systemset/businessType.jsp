<%@ taglib prefix="permissionsCode" uri="http://com.sunll.crm.permissionsTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 自定义业务字段</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/srcComF.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/CustomFields.css">
    <style>
        [v-cloak] {
            display: none;
        }
        <%--改动间距--%>
        .header_top{
            height: 50px;
            line-height: 50px;
        }
        .ContentAll .navList {
            height: 50px;
            line-height: 50px;
        }
        .ContentAll .content .table_box table tr th {
            height: 50px;
            line-height: 50px;
        }
        .ContentAll .content .table_box table tr td {
            height: 50px;
            line-height: 50px;
        }
        .ContentAll .content .table_box table tr{
            background: #fff;
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
        .ContentAll .navList div{
            font-size: 14px;
        }
        .active{
            border-bottom: 2px solid #5caaf2;
            color:#5caaf2!important;
        }
        .navList > div{
            cursor: pointer;

        }
        .ContentAll .navList div{
            color:#999999;
        }
        .Trleft{
            text-align: left!important;
            padding-left:30px;
        }
        .ContentAll .content .table_box table tr{
            background: #fff;
        }
    </style>
</head>
<body>

<div id="customFields">
    <div class="header_top">
        <span class="pull-left">自定义业务字段</span>
    </div>
    <!--tab+列表-->
    <div class="ContentAll">
        <div class="listMenu" style="width: 100%;overflow: auto;">
            <div class="navList">
                <div @click="toggle($index)"  v-for="item in dataList " :class="{active: active == $index}">
                    {{item.name}}
                    <!--<span class="line"></span>-->
                </div>
            </div>
        </div>
        <div class="content">
            <div  v-for="table in dataList" class="table_box"  v-show="active == $index">
                <table >
                    <thead>
                    <tr style="background: #f5f5f5!important;">
                        <th class="font14 Trleft">业务名称</th>
                        <th class="font14">显示名称</th>
                        <th class="font14" style="width:30%;">是否应用</th>
                        <th class="font14" style="width:20%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="row in table.businessTypeList">
                        <td class="Trleft">{{ row.name }}</td>
                        <td>{{ row.showName }}</td>
                        <td style="width:30%;">{{ row.isEnabled == 1 ? '是' : '否' }}</td>
                        <td style="width:20%;">
                            <permissionsCode:hasPermissions permissionsCode="business_field:disable" CRM_TOKEN="${cookie.permissionsKey.value}">
                            <i class="fa fa-ban text-danger cursor" v-if="row.isEnabled == 1" @click="isEnabledButton(row.id,row.name,2)" > 禁用</i>
                            <i class="fa fa-ban text-navy cursor" v-else="" @click="isEnabledButton(row.id,row.name,1)">启用 </i>
                            </permissionsCode:hasPermissions>
                            <permissionsCode:hasPermissions permissionsCode="business_field:update" CRM_TOKEN="${cookie.permissionsKey.value}">
                            <i @click="editButton(row.id,row.name,row.showName)" class="fa fa-pencil-square-o text-success cursor"> 编辑</i>
                            </permissionsCode:hasPermissions>
                            <permissionsCode:hasPermissions permissionsCode="business_field:configure" CRM_TOKEN="${cookie.permissionsKey.value}">
                            <i class="fa fa-file-text-o text-primary cursor" @click="businessTypeFieldSet(row.id)"> 配置</i>
                            </permissionsCode:hasPermissions>
                        </td>
                    </tr>

                    </tbody>

                </table>
            </div>
            <!-- 编辑-->
            <div class="editShow">
                <h4>编辑类型</h4>
                <div>
                    <span class="pull-left">系统名称：</span>
                    <input type="hidden" id="businessTypeId">
                    <span id="businessName"></span>
                </div>
                <div>
                    <span class="pull-left">显示名称：</span>
                    <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                           id="businessShowName" placeholder="请输入显示名称">
                </div>
                <div class="posttio">
                    <button type="button" class="btn btn-w-m btn-primary" @click="editSavleButton">保存</button>
                    <button type="button" class="btn btn-w-m btn-default" @click="editCancelButton">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/businessType.js"></script>

</html>