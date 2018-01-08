<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人脉旺</title>
    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/src/css/clue-add.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">

    <style>
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
        }
        input::-webkit-outer-spin-button {
            -webkit-appearance: none;
        }
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div id="container" class="wrap animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>新建线索</h5>
                    <div class="ibox-tools border-bottom pm">
                        <button @click="reWindow" class="btn btn-primary btn-circle" type="button">
                            <i class="fa fa-mail-reply"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div v-for="row in rowList" class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title title-wrap">
                    {{row.fieldName}}
                    <div class="pull-right"><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></div>
                </div>
                <div class="ibox-content row">
                    <div v-for="col in row.businessTypeFieldList" class="form-inline"
                         :class="col.fieldType != 'text' ? 'col-md-6' : 'col-md-12'">
                        <div class="m-b">
                            <label :class="col.isRequired == 1 ? 'must' : ''">{{col.fieldShowName}}</label>
                            <!-- todo：输入框 -->
                            <input v-if="col.fieldType == 'input' && col.fieldAlias == 'phone'"
                                   :placeholder="col.enterPrompt"
                                   type="text" class="form-control ce1"
                                   :class="col.error == true ? 'error' : ''"
                                   maxlength="11"
                                   v-model="col.value" @blur="regPhone(col.value)"
                            >
                            <input v-if="col.fieldType == 'input' && col.fieldAlias == 'fixedTel'"
                                   :placeholder="col.enterPrompt"
                                   type="number" class="form-control ce2"
                                   :class="col.error == true ? 'error' : ''"
                                   <%--maxlength="12"--%>
                                   v-model="col.value" @blur="regTelphone(col.value)"
                            >
                            <input v-if="col.fieldType == 'input' && col.fieldAlias != 'phone' && col.fieldAlias != 'fixedTel' && col.fieldAlias != 'textFollowTime'"
                                   :placeholder="col.enterPrompt"
                                   type="text"  class="form-control ce3"
                                   :class="col.error == true ? 'error' : ''"
                                   v-model="col.value"
                            >

                            <!-- todo：下拉列表 -->
                            <select v-if="col.fieldType == 'select'"
                                    v-model="col.value"
                                    class="form-control">
                                <option v-for="option in col.selectList">{{option.name}}</option>
                            </select>
                            <!-- todo：日历 -->
                            <div v-if="col.fieldType == 'date'" class="input-group date-wrap">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input :id="'date'+col.id" type="text" class="form-control date" v-model="col.value"
                                       :placeholder="col.enterPrompt">
                            </div>
                            <!--  todo：多行编辑 -->
                            <textarea v-if="col.fieldType == 'text'"
                                      v-model="col.value"
                                      class="form-control"
                                      :placeholder="col.enterPrompt"></textarea>
                            <span v-show="col.error" class="text-warning">* 此项必填</span>

                            <!--<button v-if="" class="btn btn-white m-l">查重</button>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content border-none">
                    <button class="btn btn-w-m btn-primary " @click="save">保存</button>
                    <button class="btn btn-w-m btn-default m-l">取消</button>
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
<script src="${pageContext.request.contextPath}/static/dist/js/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/inspinia.js"></script>
<!--<script src="./dist/js/pace.min.js"></script>-->
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script> <!-- 改成你的路径 -->
<script src="${pageContext.request.contextPath}/static/src/js/clue-add.js"></script>
</html>