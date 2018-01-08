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

    ul.sub-menu {
        display: none;
        list-style: none;
        margin-top:0px;
        position: absolute;
        padding-left:0px !important;
        padding-right:0px !important;
        background-color: rgba(204, 195, 195, 1);
        z-index: 999;
        /*filter: alpha(opacity=100);*/
    }
    ul.sub-menu > li > a{
        color: #000;
    }
    ul.sub-menu > li:hover{ background:#ff0;}
    ul.sub-menu > li {
        padding-left:15px;
        padding-right:25px;
    }
</style>
<body>
<!-- content start -->
<div class="admin-content" id="content">
    <div class="admin-content-body">
        <div class="am-tabs"  data-am-tabs="{noSwipe: 1}">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li id="manage_tab1" class="am-active">
                    <a id="myTitle" href="#">我的通话记录▼</a>
                    <ul class="sub-menu">
                        <li value="1">我的通话记录</li>
                        <li value="2">下属通话记录</li>
                        <li value="3">全部通话记录</li>
                    </ul>
                </li>
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
                            <label  class="am-u-sm-2 am-form-label" style="width: 145px;">主叫/被叫号码：</label>
                            <input  type="text" id="telNumberQ" class="am-form-field" placeholder="" style="width: 200px;float: left;">
                        </div>
                        <div class="am-form-group">
                            <label  class="am-u-sm-2 am-form-label">座席姓名：</label>
                            <input  type="text" id="seatNameQ" class="am-form-field" placeholder="" style="width: 200px;float: left;">
                        </div>
                        <div class="am-form-group" style="width: 200px;">
                            <button onclick="reloadPage(1);" class="am-btn am-btn-warning" style="padding-right: 0;width: 100px;margin-left: 150px;">查询</button>
                        </div>
                    </div>
                    <hr>
                    <table class="am-table am-table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>呼叫类型</th>
                            <th>呼叫时间</th>
                            <th>主叫号码</th>
                            <th>被叫号码</th>
                            <th>呼叫结果</th>
                            <th>座席姓名</th>

                            <th>计费时长（秒）</th>
                            <th>费用（元）</th>



                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in json">
                            <td>{{$index + 1}}</td>
                            <td>{{item.direction}}</td>
                            <td>{{item.startStamp}}</td>
                            <td>{{item.callerIdNumber}}</td>
                            <td>{{item.destinationNumber}}</td>
                            <td>成功</td>
                            <td>张三（1001）</td>

                            <td>{{item.billsec}}</td>
                            <td>{{item.fee}}</td>



                            <td>
                                <a class="am-btn am-btn-link nava" style="padding: 0;" onclick="shows('{{$index}}')">详情</a>
                            </td>
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

<!--   //编辑-->
<div class="tab_wu" style="display:none;">
    <div class="am-cf am-padding am-padding-bottom-0 am-padding-top-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">通话详情</strong>
        </div>
    </div>
    <hr style="margin: 10px 0px 0px 0px;">
    <form class="am-form am-form-success" style="margin-top: 20px;">
        <fieldset>
            <legend style="font-size: 1.4rem;font-weight: bold">通话基本信息</legend>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:80px;padding:0px;">呼叫类型：</label>
                <span id="direction" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:80px;padding:0px;">主叫号码：</label>
                <span id="caller" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:80px;padding:0px;">呼叫时间：</label>
                <span id="callTime" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:80px;padding:0px;">被叫号码：</label>
                <span id="called" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
        </fieldset>

        <fieldset>
            <legend style="font-size: 1.4rem;font-weight: bold">座席信息</legend>
        </fieldset>
        <fieldset>
            <legend style="font-size: 1.4rem;font-weight: bold">满意度评价</legend>
        </fieldset>
        <fieldset>
            <legend style="font-size: 1.4rem;font-weight: bold">通话费用</legend>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:120px;padding:0px;">计费时长（秒）：</label>
                <span id="billsec" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:100px;padding:0px;">费用（元）：</label>
                <span id="fee" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
        </fieldset>
        <fieldset style="display: none;">
            <legend style="font-size: 1.4rem;font-weight: bold">录音</legend>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:120px;padding:0px;">录音时长（秒）：</label>
                <span id="billsecR" style="position:relative;float:left;display:inline-block;padding-right:20px;"></span>
            </div>
            <div>
                <label  class="am-u-sm-2 am-form-label" style="width:100px;padding:0px;">录音文件：</label>
                <audio id="myaudio" controls="" preload="none" autobuffer="" src="http://freeswitch.admin.weiyingjia.org/Public/record/53827816503_2017-12-19-23-16-36.wav">
                    您的浏览器不支持 audio 标签。
                </audio>
            </div>
        </fieldset>



    </form>
    <div class="am-form-group">
        <button  class="am-btn am-btn-default" style="width: 120px;" onclick="hides()">返回</button>
    </div>

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
    var scopeFlag = "1";

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
        reloadPage(1);
        //query condition end
    });


    function shows(ind) {
        //alert("shows start ind : " + onepagedata[ind]);
        debugger;
        var index = layer.load(0, {shade: [0.3,'#000']});


        $("#direction").empty();
        $("#caller").empty();
        $("#callTime").empty();
        $("#called").empty();

        $("#billsec").empty();
        $("#fee").empty();

        $("#direction").append(onepagedata[ind].direction);
        $("#caller").append(onepagedata[ind].callerIdNumber);
        $("#callTime").append(onepagedata[ind].startStamp);
        $("#called").append(onepagedata[ind].destinationNumber);

        $("#billsec").append(onepagedata[ind].billsec);
        $("#fee").append(onepagedata[ind].fee);

        //billsecR
        $("#billsecR").append(onepagedata[ind].billsec);

        layer.close(index);
        $('.admin-content-body').hide();
        $('.tab_wu').show();

    }

    function hides() {
        $('.admin-content-body').show();
        $('.tab_wu').hide();
    }

    var onepagedata;
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
            url: "${pageContext.request.contextPath}/callRecord/getData?pageNumber="+pageNumber+"&pageSize="+pageSize,
            type:'POST',
            async:false,
            data:{"USER_TOKEN":sessionStorage.getItem("USER_TOKEN"),
                "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                "scopeFlag": scopeFlag,
                "createTimeFrom":createTimeFrom,
                "createTimeTo":createTimeTo,
                "direction":direction,
                "telNumber":telNumber,"seatName":seatName},
            dataType:"json",
            error:function(){
                $(this).addClass("done");
            },
            success: function(data){
                pageInfo.ts=data.total;
                pageInfo.dq=data.pageNum;
                pageInfo.all=data.pages;

                for (var i = 0; i < data.list.length; i++) {
                    //alert(data.list[i].customerType)
                    data.list[i].direction = customerTypeList.get(data.list[i].direction);
                    data.list[i].startStamp = new Date(parseInt(data.list[i].startStamp)).toLocaleString('chinese', {hour12: false});
                };

                //add by szq onepagedata
                onepagedata = data.list;
                vm.$set('json',data.list);
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

    $(document).ready(function() {
        $('#manage_tab1').click(function() {
            $(this).find('.sub-menu').slideToggle();
        });
        //ul.sub-menu myTitle
        $('ul.sub-menu > li').click(function() {
            $('a#myTitle').empty().append($(this).text()+"▼");
            scopeFlag = $(this).val();
            reloadPage(1);
        });
    });

</script>

</body>

</html>
