<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shizhiqiang
  Date: 2017-12-13
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js">

<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/oumeng/jquery-ui-1.9.2.custom.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/oumeng/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/oumeng/yonghuliebiao.css">
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    #content{
        margin-top: 20px;
    }
    .am-btn{
        border-radius: 5px;
    }
    input,select{
        color:#848181;
    }
    .am-table>caption+thead>tr:first-child>td, .am-table>caption+thead>tr:first-child>th, .am- table>colgroup+thead>tr:first-child>td, .am-table>colgroup+thead>tr:first-child>th, .am- table>thead:first-child>tr:first-child>td, .am-table>thead:first-child>tr:first-child>th {
        border-top: 0;
        font-size: 1.4rem;
    }
    .am-table>tbody>tr>td, .am-table>tbody>tr>th, .am-table>tfoot>tr>td, .am-table>tfoot>tr>th, .am- table>thead>tr>td, .am-table>thead>tr>th {
        padding: .7rem;
        line-height: 1.6;
        vertical-align: top;
        border-top: 1px solid #ddd;
        font-size: 1.4rem;
    }
</style>
<body>
<!-- content start -->
<div class="admin-content" id="content">
    <div class="admin-content-body">
        <div class="am-tabs"  data-am-tabs="{noSwipe: 1}">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li id="manage_tab1" class="am-active"><a href="#tab1">通话记录统计</a></li>
            </ul>

            <div class="am-tabs-bd am-tabs-bd-ofv">
                <div class="am-tab-panel am-active" id="tab1">
                    <div class="am-form-inline" role="form">
                        <div class="am-form-group">
                            <label  class="am-u-sm-2 am-form-label">时间：</label>
                            <input id="createTimeFrom" style="margin-bottom: 10px;width: 205px;float: left;" type="text" class="am-form-field" placeholder="开始时间" name="createTimeFrom" data-am-datepicker readonly required/>
                            <label  class="am-u-sm-2 am-form-label" style="width: 20px;"> ~</label>
                            <input id="createTimeTo" style="margin-bottom: 10px;width: 205px;float: left;margin-left:5px;" type="text" class="am-form-field" placeholder="结束时间" name="createTimeTo"  data-am-datepicker readonly required/>
                            <a class="am-btn am-btn-link nava" style="padding: 0;margin-left:25px;" onclick="setQueryDate('day')">最近三天</a>
                            <a class="am-btn am-btn-link nava" style="padding: 0;margin-left:25px;" onclick="setQueryDate('week')">最近一周</a>
                            <a class="am-btn am-btn-link nava" style="padding: 0;margin-left:25px;" onclick="setQueryDate('month')">最近一月</a>
                        </div>
                    </div>
                    <div class="am-form-inline" role="form">
                        <div class="am-form-group">
                            <label  class="am-u-sm-2 am-form-label">呼叫类型：</label>
                            <select id="directionQ" name="directionQ" class="am-form-field" style="width: 100px;float: left;-webkit-appearance:menulist;">
                                <option value="">呼叫类型</option>
                                <option value="1">呼入</option>
                                <option value="2">呼出</option>
                            </select>
                        </div>

                        <div class="am-form-group">
                            <label  class="am-u-sm-2 am-form-label">部门/人员：</label>
                            <%--<input  type="text" id="seatNameQ" class="am-form-field" placeholder="" style="width: 200px;float: left;">--%>
                            <div type="text" class="am-form-field" style="width: 150px;float: left;" id="departmentName"></div>
                            <button @click="sendOther()" type="button" class="am-form-field btn btn-primary" style="width: 50px;float: left;">
                                <i class="fa fa-ellipsis-v" aria-hidden="true">选择</i>
                            </button>
                        </div>
                        <div class="am-form-group" style="width: 300px;">
                            <button onclick="reloadPage(1);" class="am-btn am-btn-warning" style="padding-right: 0;width: 100px;margin-left: 250px;">开始统计</button>
                        </div>
                    </div>
                    <hr>
                    <%--/*部门/人员 外呼总数 外呼成功数 外呼失败数 外呼成功率  转化率 通话总时长*/--%>
                    <table class="am-table am-table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>部门/人员</th>
                            <th>外呼总数</th>
                            <th>外呼成功数</th>
                            <th>外呼失败数</th>
                            <th>外呼成功率</th>
                            <th>通话总时长（秒）</th>

                        </tr>
                        </thead>
                        <tbody id="myTbody">
                        <tr v-for="item in json">
                            <td>{{$index + 1}}</td>
                            <td>{{item.userName}}</td>
                            <td>{{item.outBoundTotal}}</td>
                            <td>{{item.outBoundSuccess}}</td>
                            <td>{{item.outBoundFailure}}</td>
                            <td>{{item.outBoundPercentage}}</td>
                            <td>{{item.callTimeTotal}}</td>

                        </tr>
                        </tbody>
                    </table>
                    <hr>
                    <div class="am-cf">
                        共 <span>{{page.ts}}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;每页显示
                        <select id="myPageSize"><option value="10" selected="">10</option><option value="20">20</option></select>条，共 <span>{{page.all}}</span> 页
                        <div class="am-fr">
                            <ul class="am-pagination" style="margin: 0">
                                <li><a  href="javascript:void(0)" onclick="reloadPage({{page.dq-1}})">上一页</a></li>
                                <li class="am-disabled"><a
                                        href="#"><span> {{page.dq}} </span>/<span> {{page.all}} </span></a></li>
                                <li><a href="javascript:void(0)" onclick="reloadPage({{page.dq+1}})">下一页</a></li>
                                <li class="am-disabled"><a href="#">|</a></li>
                                <li>
                                    <input type="text" id="gotoPage" style="width: 30px;height: 30px;margin-bottom: 5px;" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                                </li>
                                <li class="am-active"><a href="javascript:void(0);" onclick="gotoPage();" style="padding: .5rem .4rem;">GO</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>

<input id="excel_file" type="file" name="filename" accept="xlsx" style="display: none"/>
<script src="${pageContext.request.contextPath}/static/oumeng/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/jquery-ui-1.9.2.custom.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/ajaxfileupload.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/amazeui.min.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/order.js"></script>
<script src="${pageContext.request.contextPath}/static/oumeng/js/dateformat.js"></script>

<script>

    var pageInfo={
        ts: 0,
        dq: 0,
        all: 0
    };
    var vm = new Vue({
        el: "#content",
        data: {
            lx: "",
            hd: "",
            yys: "",
            sf: "",
            json: [],
            page: pageInfo,
            item: {}
        },
        components: {
            "my-component": {
                template: "#myTemplate",
                data: function () {
                    return {
                        show1: true
                    }
                }
            }
        },
        methods: {
            sendOther: function () {
                var sendOther = layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 0,
                    area: ['70%', '85%'],
                    content: "/jspSkip/selectDepAndUser",
                    anim: 1, btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                            var length = iframeWin.vm.peopleId.length;
                            var userIds = [];
                            var departmentIds = [];
                            for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                                if (iframeWin.vm.peopleNum[i].id[0] == "d") {
                                    var length = iframeWin.vm.peopleNum[i].id.length
                                    departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                                } else {
                                    userIds.push(iframeWin.vm.peopleNum[i].id)
                                }
                            }
                            if (departmentIds[0] != null) {
                                vm.checkedDepartmentId = departmentIds[0];
                                vm.checkedUserId = null;
                                console.log("部门id：" + vm.checkedDepartmentId);
                            } else {
                                vm.checkedDepartmentId = null;
                                vm.checkedUserId = userIds[0];
                                console.log("用户id：" + vm.checkedUserId);
                            }
                            $("#departmentName").text(iframeWin.vm.peopleName);
                            layer.close(sendOther);
                        }
                    },
                    //取消
                    btn2: function (index, layero) {
                    }
                })
            }
        }

    });

    function setQueryDate(flag){
        var now = new Date();
        if(flag=="day"){
            now.setDate(now.getDate()-3);
        }
        if(flag=="week"){
            now.setDate(now.getDate()-7);
        }
        if(flag=="month"){
            now.setMonth(now.getMonth()-1);
        }
        $("#createTimeTo").val(new Date().Format("yyyy-MM-dd"));
        $("#createTimeFrom").val(now.Format("yyyy-MM-dd"));
    }

    $(function() {
        //query condition start
        var nowDate = new Date();
        var nowString = nowDate.Format("yyyy-MM-dd");
        $("#createTimeFrom").val(nowString);
        $("#createTimeTo").val(nowString);
        //reloadPage(1);
        //query condition end
    });

    function hides() {
        $('.admin-content-body').show();
        $('.tab_wu').hide();
    }


    function reloadPage(pageNumber){
        if(pageNumber==0){
            layer.msg("当前是第一页啦，没有上一页！");
            return;
        }
        var createTimeFrom = $("#createTimeFrom").val();
        var createTimeTo = $("#createTimeTo").val();
        //directionQ   telNumberQ  seatNameQ
        var direction = $("#directionQ").val();
        var telNumber = $("#telNumberQ").val();
        var seatName = $("#seatNameQ").val();

        var pageSize = $("#myPageSize").val();
        var index = layer.load(0, {shade: [0.3,'#000']});
        //"CRM_TOKEN":sessionStorage.getItem("CRM_TOKEN")
        $.ajax({
            url: "${pageContext.request.contextPath}/records/getData?pageNumber="+pageNumber+"&pageSize="+pageSize,
            type:'POST',
            async:false,
            data:{"USER_TOKEN":sessionStorage.getItem("USER_TOKEN"),
                "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                "createTimeFrom":createTimeFrom,
                "createTimeTo":createTimeTo,
                "direction":direction,
                "checkedDepartmentId": vm.checkedDepartmentId,
                "checkedUserId": vm.checkedUserId},
            dataType:"json",
            error:function(){
                $(this).addClass("done");
            },
            success: function(data){
                pageInfo.ts=data.total;
                pageInfo.dq=data.pageNum;
                pageInfo.all=data.pages;

                //总计最后一行
                var userNameSum = "全员";
                var outBoundTotalSum = 0;
                var outBoundSuccessSum = 0;
                var outBoundFailureSum = 0;
                var outBoundPercentageSum = 0;
                var callTimeTotalSum = 0;
                //总计最后一行

                for (var i = 0; i < data.list.length; i++) {
                    //alert(data.list[i].customerType)
                    data.list[i].direction = customerTypeList.get(data.list[i].direction);
                    data.list[i].startStamp = new Date(parseInt(data.list[i].startStamp)).toLocaleString('chinese', {hour12: false});
                    data.list[i].outBoundPercentage = parseFloat(data.list[i].outBoundPercentage).toFixed(2) + "%";
                };
                debugger;
                $.ajax({
                    url: "${pageContext.request.contextPath}/records/allData",
                    type:'POST',
                    async:false,
                    dataType:"json",
                    error:function(){
                        console.log("唉呀妈呀，总计出错了！");
                    },
                    success: function(data) {
                        for (var i = 0; i < data.list.length; i++) {
                            //总计最后一行
                            //userNameSum = data.list[i].userName;
                            outBoundTotalSum = outBoundTotalSum + data.list[i].outBoundTotal;
                            outBoundSuccessSum = outBoundSuccessSum + data.list[i].outBoundSuccess;
                            outBoundFailureSum = outBoundFailureSum + data.list[i].outBoundFailure;
                            outBoundPercentageSum = parseFloat(outBoundPercentageSum)  + parseFloat(data.list[i].outBoundPercentage);
                            callTimeTotalSum = callTimeTotalSum + data.list[i].callTimeTotal;
                            //总计最后一行
                        };
                        outBoundPercentageSum = outBoundPercentageSum/data.list.length;
                        outBoundPercentageSum = outBoundPercentageSum.toFixed(2) + "%";
                    }
                });

                //总计最后一行 bug
                $("tr.dummy").remove();

                vm.$set('json',data.list);

                //总计最后一行拼接
                var lastRow = "<tr class='dummy'><td>总计</td>"+
                        "<td>"+userNameSum+"</td>"+
                        "<td>"+outBoundTotalSum+"</td>"+
                        "<td>"+outBoundSuccessSum+"</td>"+
                        "<td>"+outBoundFailureSum+"</td>"+
                        "<td>"+outBoundPercentageSum+"</td>"+
                        "<td>"+callTimeTotalSum+"</td></tr>";
                if(data.list.length>0) $("tbody#myTbody").append(lastRow);
                //总计最后一行拼接
                layer.close(index);
            }
        });
    }

    //reloadPage(1);

    function gotoPage(){
        var pagenum = $("#gotoPage").val();
        if(pagenum==''){
            layer.tips("请输入页数！","#gotoPage",{tips: 3});
            return;
        }
        reloadPage(pagenum);
    }

</script>

</body>

</html>
