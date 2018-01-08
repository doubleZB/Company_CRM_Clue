<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>短信群发</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!--<link href="./dist/css/animate.min.css" rel="stylesheet">-->
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/mms.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/common/height.css" rel="stylesheet">
</head>
<body class="mms fixed-sidebar no-skin-config full-height-layout">
<div id="container" class="wrap animated fadeInRight" v-cloak>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>短信群发</h5>
                    <div class="ibox-tools">
                        <button class="btn btn-outline btn-primary m-r-sm" type="button" @click="exportExcel()"><i
                                class="fa fa-pie-chart"></i>&nbsp;导出报表
                        </button>
                    </div>
                    <!-- 导出数据 -->
                    <form id="exf" action="/message/exportMessageList" method="post">
                        <input type="hidden" name="CRM_TOKEN">
                        <input type="hidden" name="startTime">
                        <input type="hidden" name="endTime">
                        <input type="hidden" name="PageNum">
                        <input type="hidden" name="PageSize">
                        <input type="hidden" name="checkedDepartmentId">
                        <input type="hidden" name="checkedUserId">
                        <input type="hidden" name="flag">
                        <input type="hidden" name="type">
                    </form>
                </div>
                <div class="ibox-content form-inline">
                    <div class="form-group m-r">
                        <label class=" control-label">场景：</label>
                        <select class="form-control" id="selectServiceScene" @change="fnChangeServiceScene" v-model="serviceSceneStr">
                            <option v-for="item in serviceScene" :value="item.id">{{ item.name }}</option>
                        </select>
                    </div>
                    <div class="form-group m-r">
                        <label class="control-label">提交日期：</label>
                        <input id="date" type="text" class="form-control date"
                               placeholder="请选择日期">
                    </div>
                    <div class="form-group m-r">
                        <label class="control-label">发送状态：</label>
                        <select class="form-control" id="selectServiceType">
                            <option v-for="item in serviceType" :value="item.id">{{ item.name }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">部门/人员：</label>
                        <div class="input-group">
                            <div type="text" class="form-control inline w90" id="departmentName"></div>
                            <span class="input-group-btn">
                                <button @click="sendOther()" :disabled="userStatus" type="button" class="btn btn-primary">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group" @click="query()">查询</div>
                </div>
            </div>
        </div>
    </div>
    <div class="main">
        <!-- 表格部分 -->
        <div class="main-content-wrap">
            <!-- 表头 -->
            <table class="table table-custom table-gray-bg">
                <thead>
                <tr>
                    <th style="display:none;">ID</th>
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
                            <tr v-for="item in operate">
                                <td style="display:none;">{{item.id}}</td>
                                <td >{{item.name}}</td>
                                <td >{{item.smsStatus}}</td>
                                <td >{{item.submitTime}}</td>
                                <td >{{item.smsContent}}</td>
                                <td >{{item.sendNumber}}</td>
                                <td >{{item.arrivalNumber}}</td>
                                <td>
                                    <span data-toggle="modal" data-target="#smsModule" class="text-danger" @click="resendMessage(item.id)">重发</span>
                                </td>
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

                        <a class="btn btn-white btn-sm btn-bitbucket" @click="lastPage()"><i class="fa fa-caret-left"></i></a>
                        <label class="control-label">{{pageNum}} / {{pages}}</label>
                        <a class="btn btn-white btn-sm btn-bitbucket" @click="nextPage()"><i class="fa fa-caret-right"></i></a>
                    </div>
                    <div class="inline clearfix">
                        <input type="number" class="form-control input-sm pull-left" min="1" id="jumpPage">
                        <button class="btn btn-sm  btn-default m-l" type="button" @click="jumpPage()">跳转</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--todo： 模态框部分  -->
    <!-- 短信模板 -->
    <div class="modal fade" id="smsModule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">发送短信</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group form-inline">
                        <label class="must margin-none">短信模版：</label>
                        <select class="form-control" v-model="smsModuleVal">
                            <option v-for="item in smsModule" :value="item">{{ item.name }}</option>
                        </select>
                    </div>
                    <div class="form-group ">
                        <label>预览</label>
                        <div class="textarea form-control">{{smsModuleVal.content}}</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary"@click="sendMessages()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
<script src="${pageContext.request.contextPath}/static/dist/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script src="${pageContext.request.contextPath}/static/src/js/mms.js"></script>
</html>