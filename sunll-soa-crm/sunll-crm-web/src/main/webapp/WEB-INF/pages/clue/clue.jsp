<%@ taglib prefix="permissionsCode" uri="http://com.sunll.crm.permissionsTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人脉旺</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!--<link href="./dist/css/animate.min.css" rel="stylesheet">-->
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/clue.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/common/height.css" rel="stylesheet">
    <style>
        table.table.table-custom th, table.table.table-custom td {
            height: 50px;
        }
        .mask{
            position: absolute;
            display: none;
            z-index: 2001;
            top: 0px;
            left: 0px;
            width: 100%;
            height: 100%;
            background-color: rgba(25, 25, 21, 0.7)
        }
        .box{
            width: 200px;
            height: 100px;
            background: #fff;
            margin: 100px auto;
            border-radius: 5px;
        }
        .font{
            font-size: 16px;
            height: 16px;
            line-height: 6;
            margin-left: 16px;
        }
        .boxMin{
            width: 125px;
            height: 75px;
            background: #fff;
            margin: 0 auto;
            margin-top: 230px;
            border-radius: 5px;
            padding-bottom: -5px;
        }
        .daoRu{
            font-size: 16px;
            height: 15px;
            line-height: 5;
            margin-left: 27px;
        }

    </style>

</head>
<body class="clue fixed-sidebar no-skin-config full-height-layout">
<div id="container" class="wrap animated fadeInRight" v-cloak>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>{{businessName}}</h5>
                    <div class="ibox-tools">
                        <permissionsCode:hasPermissions permissionsCode="clue:import" CRM_TOKEN="${cookie.permissionsKey.value}">
                        <button data-toggle="modal" data-target="#incClue"
                                class="btn btn-outline btn-primary m-r-sm" type="button">
                            <i class="fa fa-share-square-o"></i>&nbsp;导入线索
                        </button>
                        </permissionsCode:hasPermissions>
                        <permissionsCode:hasPermissions permissionsCode="clue:export" CRM_TOKEN="${cookie.permissionsKey.value}">
                        <button class="btn btn-outline btn-primary m-r-sm" type="button" @click="exportClue()"><i
                                class="fa fa-download"></i>&nbsp;导出线索
                        </button>
                        </permissionsCode:hasPermissions>
                        <permissionsCode:hasPermissions permissionsCode="clue:add" CRM_TOKEN="${cookie.permissionsKey.value}">
                        <button @click="iframeHref1()"
                                class="btn btn-outline btn-primary " type="button">
                            <i class="fa fa-plus"></i>&nbsp;新建线索
                        </button>
                        </permissionsCode:hasPermissions>
                    </div>
                </div>
                <div class="ibox-content">
                    <label class="control-label">业务类型：</label>
                    <select class="form-control m-r" @change="checkBusiness" v-model="businessSceneFont">
                        <option v-for="item in serviceType" :value="item.id">{{ item.name }}</option>
                    </select>
                    <label class=" control-label">场景：</label>
                    <select class="form-control" @change="checkIdentifier" v-model="serviceSceneFont">
                        <option v-for="item in serviceScene" :value="item.id">{{ item.name }}</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="main ibox-content po-rela">
        <div class="row m-b">
            <div class="col-xs-6">
                <permissionsCode:hasPermissions permissionsCode="clue:sms" CRM_TOKEN="${cookie.permissionsKey.value}">
                <button :disabled="have" class="btn btn-outline btn-primary m-r-sm smsY" type="button">
                    <%--<button type="button" class="btn btn-primary" @click="fnSmsSend()">--%>
                    <i class="fa fa-envelope-o" ></i>&nbsp;
                    <span data-toggle="modal"  data-target="#smsModule" class="smsShow">短信批发</span>
                    <%--批发短信--%>
                </button>
                </permissionsCode:hasPermissions>
                <permissionsCode:hasPermissions permissionsCode="clue:transfer" CRM_TOKEN="${cookie.permissionsKey.value}">
                <button :disabled="have" @click="sendOther()"
                        class="btn btn-outline btn-primary  m-r-sm" type="button">
                    <i class="fa fa-retweet"></i>&nbsp;转让
                </button>
                </permissionsCode:hasPermissions>
                <permissionsCode:hasPermissions permissionsCode="clue:delete" CRM_TOKEN="${cookie.permissionsKey.value}">
                <button :disabled="have" class="btn btn-outline btn-primary  m-r-sm" type="button" @click="delClue()">
                    <i class="fa fa-trash"> </i>&nbsp;删除
                </button>
                </permissionsCode:hasPermissions>
            </div>
            <div class="col-xs-6 form-inline text-right">
                <div class="input-group m-r">
                    <!-- 联系人 -->
                    <select class="form-control b-radio-3" v-model="serviceClueField">
                        <option value="">请选择</option>
                        <option v-for="item in selectClueField" :value="item.id">{{ item.fieldShowName }}</option>
                    </select>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" id="fieldValue">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-primary" @click="searchFieldValue()"><i
                                class="fa fa-search"></i></button>
                    </span>
                </div>
            </div>
        </div>
        <%--<div>--%>
        <%--<dl>--%>
        <%--<dt>filters</dt>--%>
        <%--<dd>{{ showFiled }}</dd>--%>
        <%--</dl>--%>
        <%--<dl>--%>
        <%--<dt>currentSelected</dt>--%>
        <%--<dd>{{currentSelected}}</dd>--%>
        <%--</dl>--%>
        <%--</div>--%>
        <!-- 表格部分 -->
        <div class="main-content-wrap row">
            <!-- 表头 -->
            <table class="table table-custom table-blue-bg">
                <thead>
                <tr>
                    <th class="po-rela dropdown w120">
                      <%--  <input type="checkbox"  class="checkboxAll" id="checkboxAll"
                               @change="setAllSelected($event.target.checked)"/>--%>
                        <input type="checkbox" :checked="isAllSelected" class="checkboxAll"
                               @change="setAllSelected($event.target.checked)"/>
                    </th>

                    <th v-for="header in showFiled" class="po-rela dropdown">
                        <template v-if="true">{{ header.fieldShowName }}</template>
                        <template v-else>
                            <a @click="fnStatusListType()" class="dropdown-toggle">
                                {{ row.fieldName }}<b class="caret"></b>
                            </a>
                            <ul v-if="statusListType" class="dropdown-menu animated fadeIn" style="display: block">
                                <li v-for="item in statusList">

                                    <label> <input type="checkbox"> &nbsp;&nbsp;&nbsp;{{ item.name }}</label>
                                </li>
                                <li>
                                    <button class="btn btn-xs btn-primary " type="submit">保存</button>
                                    <button class="btn btn-xs btn-default m-l" @click="statusListType = false"> 取消
                                    </button>
                                </li>
                            </ul>
                        </template>
                    </th>
                    <th class="po-rela w200">
                        操作
                        <span @click="operateType = !operateType" class="po-abso"><i class="fa fa-gear"></i></span>
                    </th>
                </tr>
                </thead>
            </table>
            <!-- 表体 -->
            <div class="main-table-wrap fh-breadcrumb">
                <div class="full-height">
                    <div class="full-height-scroll">
                        <table class="table table-hover table-custom">
                            <tbody>
                            <tr v-for="item, index in filtedData">
                                <td class="w120"><input :id="item.filedId" type="checkbox"
                                                        :checked="currentSelected.indexOf(item.filedId) !== -1"
                                                        @change="changeItem($event, item, index)"
                                                        class="iptcheckbox"/>
                                </td>
                                <td v-for="filter in showFiled">{{ item[filter.id] }}</td>
                                <td class="w200">
                                    <span data-toggle="modal" @click="getColId(item.filedId)" data-target="#smsModule" class="text-danger">短信</span> |
                                    <span @click="skipCall(item.filedId)" class="text-success">呼叫</span> |
                                    <span @click="skipDetail(item.filedId)">详情</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- todo： 操作弹窗 -->
        <div v-show="operateType" class="operate-wrap po-abso animated-fast fadeInRight">
            <h3 class="p-m m-n">表格自定义 <span class="text-success pull-right">最多可筛选8个字段</span></h3>
            <div class="fh-breadcrumb">
                <div class="full-height">
                    <div class="full-height-scroll white-bg border-left">
                        <table class="table table-striped operate-table">
                            <thead>
                            <tr>
                                <th>显示/不显示</th>
                                <th></th>
                                <th>筛选字段</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="filter in allFiled" id="inputsParent">
                                <td><input type="checkbox" :id="filter.id" :checked="filter.select"
                                               @click="changeFilter(filter,$event)"/></td>
                                <td></td>
                                <td><label :for="filter.id">{{filter.fieldShowName}}</label></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="p-m">
                <button class="btn btn-w-m btn-primary " type="submit" @click="saveFieldShow()">保存</button>
                <button class="btn btn-w-m btn-default m-l" @click="operateType = false">取消</button>
            </div>
        </div>

        <!-- 分页 -->
        <div class="row p-sm form-inline page">
            <div class="col-sm-1">
                <label class="control-label" style="white-space: nowrap">已选{{ currentSelected.length }}条</label>
            </div>
            <div class="col-sm-11 text-right">
                <div class="inline m-r">
                    <label class=" control-label">每页显示</label>
                    <select class="input-sm form-control input-s-sm" @change="changePageSize" v-model="PageSizeFont">
                        <option v-for="item in paging" :value="item">{{item}}</option>
                    </select>
                    <label class=" control-label m-r">条，共{{pages}}页</label>

                    <a class="btn btn-white btn-sm btn-bitbucket" @click="PreviousPage()"><i
                            class="fa fa-caret-left"></i></a>
                    <label class="control-label">{{Page}} / {{pages}}</label>
                    <a class="btn btn-white btn-sm btn-bitbucket" @click="nextPage()"><i class="fa fa-caret-right"></i></a>
                </div>
                <div class="inline clearfix">
                    <input type="number" class="form-control input-sm pull-left" min="1" id="pageNumber">
                    <button class="btn btn-sm  btn-default m-l" type="button" @click="skipPageNumber()">跳转</button>
                </div>

            </div>
        </div>

        <!--  消息弹框 -->
        <%--<div id="winpop" style="height: 204px; display: block;" class="text-center">--%>
        <%--<div class="title">消息提醒<span class="winpop_close" onclick="tips_pop()"><B>X</B></span></div>--%>
        <%--<div class="border-bottom"></div>--%>
        <%--<div class="con">您的线索《王乐乐》需要您在2017-09-09 19:00去跟进。</div>--%>
        <%--<button type="button" class="btn btn-w-m btn-primary">查看消息</button>--%>
        <%--</div>--%>
    </div>

    <!--todo： 模态框部分  -->
    <!-- 短信模板 -->
    <div class="modal fade" id="smsModule"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">发送短信</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group form-inline">
                        <label class="must margin-none">短信模版：</label>
                        <select class="form-control" v-model="smsModuleVal" >
                            <option v-for="item in smsModule" :value="item">{{ item.name }}</option>
                        </select>
                    </div>
                    <div class="form-group ">
                        <label>预览</label>
                        <div class="textarea form-control" style="word-break: break-all;">{{smsModuleVal.content}}</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="fnSmsSend()">发送</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 导入线索 -->
    <div class="modal fade" id="incClue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">导入线索</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="form-title">请选择要导入的线索业务类型</label>
                        <div>
                            <select class="form-control m-r"  v-model="businessSceneFont">
                                <option v-for="item in serviceType" :value="item.id">{{ item.name }}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" onclick="selectCheckBox()">
                        <label class="form-title">判断手机号重复时</label>
                        <div class="clearfix">
                            <label class="checkbox-inline col-sm-4" for="r1">
                                <input type="radio" id="r1" name="phone" value="1" checked="checked">
                                覆盖导入
                            </label>
                            <label class="checkbox-inline col-sm-4" for="r2">
                                <input type="radio" id="r2" name="phone" value="2">
                                不导入
                            </label>
                            <label class="checkbox-inline col-sm-3" for="r3">
                                <input type="radio" id="r3" name="phone" value="3">
                                同时导入
                            </label>
                        </div>
                    </div>
                    <div class="form-group-lg">
                        <label class="form-title">请选择要导入的Excel文件</label>
                        <div class="error" style="margin-top: 20px;color: #ff0000;display:none;" id="errorId">
                            数据导入失败，<a href="#" style="text-decoration: underline;color:#ff0000;" id="errorMessageExcel" onclick="uploadErrorFile()">查看详情</a>

                    </div>
                        <div class="error" style="margin-top: 20px;display:none;" id="errorMessage"></div>

                        <div class="text-center">
                            <button class="btn btn-outline btn-danger " type="button"><i class="fa fa-plus" style="position: relative;"></i>
                                添加文件
                                <form id="uploadForm">
                                    <input type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" name="file" id="uploadFile"  style="width:100%;height:20%;position: absolute;left:0;bottom:0;opacity: 0;z-index: 10">
                                </form>
                                <%--<input type="file"   name="file" id="uploadFile"  style="width:100%;height:20%;position: absolute;left:0;bottom:0;opacity: 0;z-index: 10">--%>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="pull-left m-t" href="#" @click="exportClueExcel()">下载数据模板</a>
                    <%--onclick="asyncUpload('uploadFile','','')"--%>
                    <%--@click="saveUploadFile()"--%>
                    <button type="button" class="btn btn-primary"  @click="saveUploadFile()">保存
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <form id="clueForm" action="/clue/exportClue" method="post">
        <input type="hidden" id="clueData" name="apiAcceptData">
    </form>
    <form id="clueFormExcel" action="/clue/exportClueExcel" method="post">
        <input type="hidden" id="clueDataExcel" name="apiAcceptData">
    </form>
    <!-- 指定门店 -->
    <div class="modal fade" id="specifiedStore" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">指定门店</h4>
                </div>
                <div class="modal-body m-b-xl">
                    <div class="form-group form-inline">
                        <label class="must">指定门店：</label>
                        <select class="form-control">
                            <option v-for="item in specifiedStore">{{ item.name }}</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <%--弹层--%>
    <div class="mask" id="maskId">
        <div class="box">
          <span class="font">线索禁用,请联系管理员</span>
        </div>
    </div>
<%--导入中--%>
    <div class="mask" id="maskImportId" >
        <div class="boxMin" >
            <span class="daoRu">导入中...</span>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/cluevue/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/js/upload.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/clue.js"></script>
<script>
    function tips_pop() {
        var MsgPop = document.getElementById("winpop");
        var popH = parseInt(MsgPop.style.height);//将对象的高度转化为数字
        if (popH == 0) {
            MsgPop.style.display = "block";//显示隐藏的窗口
            show = setInterval("changeH('up')", 2);
        }
        else {
            hide = setInterval("changeH('down')", 2);
        }
    }

    function changeH(str) {
        var MsgPop = document.getElementById("winpop");
        var popH = parseInt(MsgPop.style.height);
        if (str == "up") {
            if (popH <= 200) {
                MsgPop.style.height = (popH + 4).toString() + "px";
            }
            else {
                clearInterval(show);
            }
        }
        if (str == "down") {
            if (popH >= 4) {
                MsgPop.style.height = (popH - 4).toString() + "px";
            }
            else {
                clearInterval(hide);
                MsgPop.style.display = "none";  //隐藏DIV
            }
        }
    }

//    window.onload = function () {    //加载
//        document.getElementById('winpop').style.height = '0px';
//        setTimeout("tips_pop()", 800);     //3秒后调用tips_pop()这个函数
//    }
    //    function tips_pop() {
    //        var MsgPop = document.getElementById("winpop");
    //        var popH = parseInt(MsgPop.style.height);//将对象的高度转化为数字
    //        if (popH == 0) {
    //            MsgPop.style.display = "block";//显示隐藏的窗口
    //            show = setInterval("changeH('up')", 2);
    //        }
    //        else {
    //            hide = setInterval("changeH('down')", 2);
    //        }
    //    }
    //
    //    function changeH(str) {
    //        var MsgPop = document.getElementById("winpop");
    //        var popH = parseInt(MsgPop.style.height);
    //        if (str == "up") {
    //            if (popH <= 200) {
    //                MsgPop.style.height = (popH + 4).toString() + "px";
    //            }
    //            else {
    //                clearInterval(show);
    //            }
    //        }
    //        if (str == "down") {
    //            if (popH >= 4) {
    //                MsgPop.style.height = (popH - 4).toString() + "px";
    //            }
    //            else {
    //                clearInterval(hide);
    //                MsgPop.style.display = "none";  //隐藏DIV
    //            }
    //        }
    //    }
    //
    //    window.onload = function () {    //加载
    //        document.getElementById('winpop').style.height = '0px';
    //        setTimeout("tips_pop()", 800);     //3秒后调用tips_pop()这个函数
    //    }

//    $("input[name='b']").click(function(){
//        //判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
//        if($(this).is(":checked")){
//            $("input[name='a']").prop("checked",true);
//        }else{
//            $("input[name='a']").prop("checked",false);
//        }
//    });

</script>
</html>