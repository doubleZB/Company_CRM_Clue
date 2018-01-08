<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人脉旺</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/clue-details.css" rel="stylesheet">
    <style>
        .white-bg {
            padding-bottom: 18px;
        }

        .ibox-title .col-sm-3 {
            width: 50%;
        }
    </style>
</head>
<body>
<div id="container" class="wrap animated fadeInRight" v-cloak>
    <div class="row">
        <div class="col-lg-12 top-row">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>线索详情</h5>
                    <div class="ibox-tools border-bottom pm">
                        <button class="btn btn-primary btn-circle" type="button" @click="back()">
                            <i class="fa fa-mail-reply"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12 white-bg ibox">
            <div class="ibox-title">
                <div class="col-md-6 user-info-wrap" style="padding-right: 0">
                    <div v-for="col in userInfo"
                         class="col-sm-3 form-inline {{ $index > 1 ? 'showAll' : ''}}">
                        <label> {{ col.fieldShowName}}： </label>
                        <span>{{ col.value }}</span>
                    </div>
                </div>
                <div class="col-md-6 form-inline text-right m-b-sm">
                    <select v-if="isShowFollowStatus=1" class="form-control m-r m-t-sm" v-model="showFollowStatus"
                            @change="changeFollowStatus">
                        <option v-for="status in followStatus" :value="status.value">{{ status.name }}</option>
                    </select>
                    <%--<select class="form-control m-r">
                        <option>未联系</option>
                        <option>option 2</option>
                        <option>option 3</option>
                        <option>option 4</option>
                    </select>--%>
                    <button class="btn btn-outline btn-primary m-r-sm" @click="sendOther()">
                        <i class="fa fa-share-square-o"></i>&nbsp;转移
                    </button>
                    <button class="btn btn-outline btn-primary m-r-sm" data-toggle="modal" data-target="#smsModule"
                            v-if="isShowPointStore=1">
                        <i class=" fa fa-bank"></i>&nbsp;指定门店
                    </button>
                    <%--<button @click="iframeHref('/jspSkip/updateClue')" class="btn btn-outline btn-primary m-r-sm">--%>
                    <button @click="updateClue()" class="btn btn-outline btn-primary m-r-sm">
                        <i class="fa fa-edit"></i>&nbsp;编辑
                    </button>
                    <button class="btn btn-outline btn-primary m-r-sm" @click="skipCall()">
                        <i class="fa fa-phone"></i>&nbsp;呼叫
                    </button>
                    <a class="collapse-link" @click="fnShowAll">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content border-none pm0">
                <div v-for="(index,row) in rowList" class="row" v-if="index >= 1">
                    <div class="col-lg-12">
                        <div class="ibox-title title-wrap">{{ row.fieldName }}</div>
                        <div class="ibox-content border-none row line-h-3">
                            <div v-for="col in row.businessTypeFieldList"
                                 class="{{ col.fieldType != 'text' ? 'col-sm-3' : 'col-sm-12' }} form-inline">
                                <label class="{{ col.isRequired == 1 ? 'must' : '' }}">
                                    {{ col.fieldShowName}}：
                                </label>
                                <span>{{ col.value }}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox-title title-wrap">系统信息</div>
                        <div class="ibox-content border-none row line-h-3">
                            <div class="col-sm-3 form-inline">
                                <label class="must">
                                    负责人：
                                </label>
                                <span>{{clueSystemInfo.principalName}}</span>
                            </div>
                            <div class="col-sm-3 form-inline">
                                <label class="must">
                                    更新时间：
                                </label>
                                <span>{{clueSystemInfo.updateTime | time}}</span>
                            </div>
                            <div class="col-sm-3 form-inline">
                                <label class="must">
                                    创建人：
                                </label>
                                <span>{{clueSystemInfo.createName}}</span>
                            </div>
                            <div class="col-sm-3 form-inline">
                                <label class="must">
                                    创建时间：
                                </label>
                                <span>{{clueSystemInfo.createTime | time}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 跟進記錄 -->
    <div class="row white-bg">
        <div class="col-lg-12 top-row">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>跟进记录（{{total}}）</h5>
                    <div class="ibox-tools border-bottom pm">
                        <button class="btn btn-primary btn-circle" type="button" style="opacity: 0;cursor: none">
                            <!--<i class="fa fa-mail-reply"></i>-->
                        </button>
                    </div>
                </div>
                <div class="ibox-content border-none">
                    <div class="ibox-title minTitle">
                        <div class="row">
                            <b class="col-sm-3 newFont">新建跟进信息</b>
                            <%--<div class="col-sm-9 text-right">
                                <span class="text-success m-r-xs"><i class="fa fa-picture-o"></i> 图片（0）</span>
                                <span class="text-navy"><i class="fa fa-folder-o"></i> 文件（0）</span>
                            </div>--%>
                        </div>
                        <textarea class="form-control contentText" placeholder="请输入您要输入的销售记录500字以内"
                                  id="followContent"></textarea>
                        <div class="form-inline m-b">
                            <select class="form-control m-r" v-model="followUp">
                                <option value="0">请选择跟进方式</option>
                                <option value="1">电话沟通</option>
                                <option value="2">上门拜访</option>
                                <option value="3">邮件沟通</option>
                                <option value="4">其他方式</option>
                            </select>

                            <input id="followTextTime" type="text" class="form-control date"
                                   placeholder="请选择下次跟进时间">

                            <input id="followReminderContent" type="email" class="form-control m-r"
                                   placeholder="请输入提醒事项，用于提醒您的展示信息">
                            <button class="btn btn-success btn-w-m" type="button" @click="saveFollowRecord">提交</button>
                        </div>

                    </div>
                </div>
            </div>
            <!-- todo：评价 -->
            <section class="comment-wrap ">
                <template v-for="followRecord in followRecordList">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title col-md-2">
                            <h5>{{followRecord.monthAndDay}}</h5>
                            <br>
                            <span class="dates">{{followRecord.year}}</span>
                            <a class="collapse-link aSize1">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                        <div class="ibox-content col-md-10">
                            <template v-for="item in followRecord.followRecordList">
                                <div class="contentTop clearfix">
                                    <img class="img-circle pull-left "
                                         src="${pageContext.request.contextPath}/static/src/images/detail1.png" alt="">
                                    <p class="pull-left m-l-sm m-t-sm">
                                        {{item.createName}}
                                        <b class="status text-navy" v-if="item.followUp == 1">电话沟通</b>
                                        <b class="status text-navy" v-if="item.followUp == 2">上门拜访</b>
                                        <b class="status text-navy" v-if="item.followUp == 3">邮件沟通</b>
                                        <b class="status text-navy" v-if="item.followUp == 4">其他方式</b>
                                        <br>
                                        {{item.showCreateTime}}
                                    </p>
                                </div>
                                <figure>
                                    <figcaption>
                                        {{item.followContent}}
                                    </figcaption>
                                    <%--<div class="img-wrap">
                                        <img src="${pageContext.request.contextPath}/static/src/images/1.png" alt="">
                                        <img src="${pageContext.request.contextPath}/static/src/images/1.png" alt="">
                                        <img src="${pageContext.request.contextPath}/static/src/images/2.png" alt="">
                                        <img src="${pageContext.request.contextPath}/static/src/images/1.png" alt="">
                                        <img src="${pageContext.request.contextPath}/static/src/images/3.png" alt="">
                                        <img src="${pageContext.request.contextPath}/static/src/images/4.png" alt="">
                                    </div>--%>
                                </figure>

                                <div class="contentFooter clearfix">
                                    来源：<b>线索</b>
                                    <p class="pull-right">
                                    <span class="cursor-pointer text-danger" @click="delFollowRecord(item.id)"><i
                                            class="fa fa-trash-o"></i> 删除</span>
                                        <%--&nbsp;|&nbsp;
                                        <span class="cursor-pointer text-muted"><i class="fa fa-pencil-square-o"></i> 点评</span>--%>
                                    </p>
                                </div>
                            </template>
                        </div>
                        <%--<div class="shrink ibox-content col-md-10">蔺长雪、王乐乐、共跟进<span class="text-danger">3</span>条记录</div>--%>
                    </div>
                </template>
                <!-- 翻页 -->
                <%--<div class="row p-sm form-inline page m-t m-r-n text-right">
                    <div class="inline m-r">
                        <label class=" control-label">每页显示</label>
                        <select class="input-sm form-control input-s-sm">
                            <option value="1" selected="">10</option>
                            <option value="0">20</option>
                        </select>
                        <label class=" control-label m-r">条，共200页</label>

                        <a class="btn btn-white btn-sm btn-bitbucket"><i class="fa fa-caret-left"></i></a>
                        <label class="control-label">1/50</label>
                        <a class="btn btn-white btn-sm btn-bitbucket"><i class="fa fa-caret-right"></i></a>
                    </div>
                    <div class="inline clearfix">
                        <input type="number" class="form-control input-sm pull-left" min="1">
                        <button class="btn btn-sm  btn-default m-l" type="button">跳转</button>
                    </div>
                </div>--%>
            </section>
        </div>
        <!--todo： 模态框部分  -->
        <!-- 短信模板 -->
        <div class="modal fade" id="smsModule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">指定门店</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group form-inline">
                            <label class="must margin-none">选择门店：</label>
                            <select class="form-control" v-model="showPointStore" @change="savePointStore()">
                                <option v-for="item in pointStore" :value="item.value">{{ item.name }}</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" @click="savePointStore()">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <input type="hidden" id="clueId" value="${clueId}">
    <input type="hidden" id="businessTypeId" value="${businessTypeId}">
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/inspinia.js"></script>
<!--<script src="./dist/js/pace.min.js"></script>-->
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/clue-details.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
    $(function () {
//        $('.aSize1').click(function () {
////      alert(1)
////      $(this).find(i).hasClass('fa-chevron-down')
//            console.log($(this).find('i').hasClass('fa-chevron-down'))
//            if ($(this).find('i').hasClass('fa-chevron-down')) {
//                $(this).parent().parent().find('.shrink').show()
//            } else {
//                $(this).parent().parent().find('.shrink').hide()
//            }
////      $(this).parent().parent()
//        })
        laydate.render({
            elem: '#followTextTime',
            type: 'datetime'
        });
    })
</script>
</html>