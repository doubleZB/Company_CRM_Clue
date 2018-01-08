<%@ taglib prefix="permissionsCode" uri="http://com.sunll.crm.permissionsTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>自定义业务类型</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/BusinessType.css">
    <style>
        <%--改动间距--%>
        [v-cloak] {
            display: none;
        }
        .table_box table tr th, .table_box table tr td {
            height: 50px;
            line-height: 50px;
        }


        .cancelSpan{
            display: inline-block;
            width:40px!important;
            text-align: center;
            font-size: 26px!important;
            cursor: pointer;
        }
        .iptPadd > input{
            margin-left:100px;
        }
        .iptPadd > input:nth-of-type(1){
            margin-left:0;
        }
        .Trleft{
            text-align: left!important;
            padding-left:30px;
        }
        .TrCenter{
            text-align: center;
        }
        .TrRight{
            text-align: right!important;
            width:35%!important;
        }
        .trColor th{
            color:#ccc!important;
        }
        .table_box tr:nth-child(2n-1) {
            background: #fff;
        }
        .table_box table tr {
            background: #fff;
        }
        body .font14{
            font-size: 14px!important;
            color:#333!important;
        }
        .cursor{
            cursor: pointer;
        }
        .iptPadd > div:nth-of-type(1){
            width:330px;
            min-height:40px;
            border:1px solid #e6e6e6;
            float:left;
        }
        .iptPadd > div:nth-of-type(1) > div{
            padding:5px 10px;
            display: inline-block;
            margin-right:10px;
            position: relative;
        }
        .iptPadd > div:nth-of-type(1) > div > div{
            position: absolute;
            right:0;
            top:3px;
            cursor: pointer;
            width:15px;
            height:15px;
            background: red;
            color:#fff;
            text-align: center;
            line-height: 15px;
            border-radius: 50%;
        }
        .iptPadd > img{
            float: left;
            width: 30px;
            height: 20px;
            margin: 10px 0 0 0px;
        }
        .AddTape {
            transition: right .6s ;
        }
    </style>
</head>
<body>
<div id="BusinessType">
    <div class="header_top">
        <span class="pull-left">自定义业务类型</span>
        <permissionsCode:hasPermissions permissionsCode="business_type:add" CRM_TOKEN="${cookie.permissionsKey.value}">
        <button type="button" @click="addOrEditBtn(1,'')" class="btn btn-w-m btn-primary pull-right">
            <i class="fa fa-plus-square-o"></i>新建类型
        </button>
        </permissionsCode:hasPermissions>
    </div>
    <div class="table_box" style="margin-top:0;">
        <table>
            <thead>
            <tr class="trColor" style=" background: #f5f5f5!important;">
                <th class="Trleft font14">{{TypeName}}</th>
                <th class="TrCenter font14">{{department}}</th>
                <th class="TrRight font14">{{Codizu}}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in TypeList">
                <td class="Trleft">{{item.name}}</td>
                <td class="TrCenter">{{item.orgNames}}</td>
                <td class="TrRight">
                    <i class="fa fa-pencil-square-o cursor" @click="addOrEditBtn(2,item.id)"> 编辑</i>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <!--显示隐藏-->
    <div class="AddTape">
        <h4>{{addOrEditTitle}}</h4>
        <div>
            <span class="pull-left">业务类型</span>
            <input type="hidden" name="businessId" id="businessId" />
            <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__" name="search" id="businessName"
                   placeholder="请输入业务类型,最长6个字符">
        </div>
        <div class="iptPadd">
            <span class="pull-left">归属部门</span>
            <div >
                <template v-for="(index,item) in departmentList">
                    <div>
                        {{item.name}}<div class="" @click="delSpan(index)">×</div>
                    </div>
                    <%--<input value="{{item.name}}" disabled="disabled"><span class="cancelSpan" @click="delSpan(index)">×</span>--%>
                </template>
            </div>
            <%--<select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="form-control   input-sm">
                <option v-for="item in typeName">{{item.names}}</option>
            </select>--%>
            <img src="${pageContext.request.contextPath}/static/images/select.png" alt=""
                 @click="skipDepartmentMultipleByUpdateUser()"/>
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
<script src="${pageContext.request.contextPath}/static/src/js/business.js"></script>
</html>