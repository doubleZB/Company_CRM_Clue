<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>统计分析费用统计</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!--<link href="./dist/css/animate.min.css" rel="stylesheet">-->
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/mms.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/common/height.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body class="mms fixed-sidebar no-skin-config full-height-layout">
<div id="container" class="wrap animated fadeInRight" v-cloak>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>统计分析费用统计</h5>
                    <div class="ibox-tools">
                        <button class="btn btn-outline btn-primary m-r-sm" type="button" @click="exportExcel()"><i
                                class="fa fa-pie-chart"></i>&nbsp;导出报表
                        </button>
                    </div>
                    <!-- 导出数据 -->
                    <%--<form id="exf" action="/call/exportTotalExpensesTotal" method="post">--%>
                    <form id="exf" action="/call/exportTotalExpensesTotalOther" method="post">
                        <input type="hidden" name="userId">
                        <input type="hidden" name="companyId">
                        <input type="hidden" name="startTime">
                        <input type="hidden" name="endTime">
                        <input type="hidden" name="PageNum">
                        <input type="hidden" name="PageSize">
                        <input type="hidden" name="checkedDepartmentId">
                        <input type="hidden" name="checkedUserId">
                    </form>
                </div>
                <div class="ibox-content form-inline">
                    <div class="form-group m-r">
                        <label class=" control-label">统计类型：</label>
                        <select v-model="statisticType" @change="fnType()" class="form-control">
                            <option v-for="item in statisticTypeArr" :value="item.name">{{ item.name }}</option>
                        </select>
                    </div>
                    <div class="form-group m-r">
                        <label class="control-label">通话日期：</label>
                        <input style="width:200px " id="date" type="text" class="form-control date"
                               placeholder="不限">
                    </div>
                    <div class="form-group">
                        <label class="control-label">部门/人员：</label>
                        <div class="input-group">
                            <div type="text" class="form-control inline w90" id="departmentName" style="overflow: hidden;white-space:nowrap"></div>
                            <span class="input-group-btn">
                                <button @click="sendOther()" type="button" class="btn btn-primary">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                    <%--<div class="form-group" @click="query()">查询</div>--%>
                    <button style="width: 40px;" type="button" class="form-group" @click="query()">查询</button>
                </div>
            </div>
        </div>
    </div>
    <div class="main">
        <!-- 表格部分 -->
        <div class="main-content-wrap">
            <!-- 表头  -->
            <table class="table table-custom table-gray-bg">
                <thead>
                <tr>
                    <th style="display: none">ID</th>
                    <th v-for="row in tableHead">{{ row.name }}</th>
                </tr>
                </thead>
            </table>
            <!-- 表体 -->
            <div class="main-table-wrap fh-breadcrumb">
                <div class="full-height">
                    <div class="full-height-scroll">
                        <table class="table table-striped table-hover table-custom">
                            <tbody>
                            <tr v-for="row in operate">
                                <td style="display: none">{{row.id}}</td>
                                <td >{{row.name}}</td>
                                <td >{{row.totalSeatCosts}}</td>
                                <td >{{row.totalCallDuration}}</td>
                                <td >{{row.callDurationTotalCost}}</td>
                                <td >{{row.total}}</td>
                            </tr>
                            <tr class="text-navy" >
                                <td style="display: none">ID</td>
                                <td>总计</td>
                                <td >{{allTotal.totalSeatCosts}}</td>
                                <td >{{allTotal.totalCallDuration}}</td>
                                <td >{{allTotal.callDurationTotalCost}}</td>
                                <td >{{allTotal.total}}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row p-sm form-inline page">
                <div class="col-sm-12 text-right">
                    <div class="inline m-r">
                        <label class=" control-label">每页显示</label>
                        <select class="input-sm form-control input-s-sm" id="checkedPageSize" v-on:change="checkedPageSize($event)">
                            <option v-for="item in pageSizeField" :value="item">{{item}}</option>
                        </select>
                        <label class=" control-label m-r">条，共{{total}}条</label>

                        <a class="btn btn-white btn-sm btn-bitbucket" @click="lastPage()"><i class="fa fa-caret-left" ></i></a>
                        <label class="control-label">{{pageNum}} / {{pages}}</label>
                        <a class="btn btn-white btn-sm btn-bitbucket" @click="nextPage()"><i class="fa fa-caret-right" ></i></a>
                    </div>
                    <div class="inline clearfix">
                        <input type="number" class="form-control input-sm pull-left" min="1" id="jumpPage">
                        <button class="btn btn-sm  btn-default m-l" type="button" @click="jumpPage()">跳转</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script> <!-- 改成你的路径 -->
<script src="${pageContext.request.contextPath}/static/src/js/statistic-cost.js"></script>
</html>