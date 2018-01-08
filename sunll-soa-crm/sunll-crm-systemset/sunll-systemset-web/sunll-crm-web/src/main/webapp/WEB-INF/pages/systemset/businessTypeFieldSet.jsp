<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 线索配置</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/srcComF.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/src/css/CuCuesConfigurationes.css">
</head>
<body>
<div id="content" v-cloak>
    <div class="header_top">
        <span class="pull-left">线索配置</span>
        <input type="hidden" id="businessTypeId" value="${businessTypeId}">
        <div class="fr"><i @click="back()" class="fa fa-arrow-circle-o-left cursor back headBtn"></i></div>
        <div class="fr"><i @click="addField()" class="fa fa-list-ol text-success cursor addField headBtn"> 添加字段</i>
        </div>
        <div class="fr"><i @click="addOrEditBlock(1,'')" class="fa fa-delicious text-success cursor addBlock headBtn"> 添加区块</i>
        </div>
    </div>
    <!--tab+列表-->
    <div class="content">
        <table class="tab">
            <tr class="tabTit">
                <td style="width: 22%;" class="fontW">字段名称</td>
                <td style="width: 22%;" class="fontW">显示名称</td>
                <td style="width: 22%;" class="fontW">启用</td>
                <td style="width: 22%;" class="fontW">必填</td>
                <td style="width: 8%;" class="fontW">操作</td>
                <td style="width: 8%;" class="fontW">排序</td>
            </tr>
        </table>
        <table v-for="item in todos" class="tab">
            <thead class="TabTitle">
            <td colspan="6">{{item.fieldName}}</td>
            </thead>
            <tbody class="tabSortOne">
            <tr v-for="todo in item.businessTypeFieldList">
                <td style="width: 22%;">{{todo.fieldName}}</td>
                <td style="width: 22%;">{{todo.fieldShowName}}</td>
                <td style="width: 22%;">
                    <input type="checkbox" name="checkbox" value="{{todo.isEnabled}}" @click="isEnabled(todo.id,$event)"/>
                </td>
                <td style="width: 22%;">
                    <input type="checkbox" name="checkbox" value="{{todo.isRequired}}" @click="isRequired(todo.id,$event)"/>
                </td>
                <td style="width: 8%;">
                    <i @click="detilTo(item.id,item.fieldName,todo,$event)"
                       class="fa fa-pencil-square-o text-success cursor" v-if="todo.isModify != 3"> 编辑</i>
                </td>
                <td style="width: 8%;">
                    <img src="${pageContext.request.contextPath}/static/src/images/icon/edit.png" class="sort cursor"
                         alt="" v-if="todo.isModify != 3"/>
                </td>
            </tr>
            </tbody>
        </table>
        <!--编辑-->
        <div class="editFieldTxt positionCont">
            <h4>修改字段</h4>
            <div>
                <span class="pull-left">区块</span>
                <select class="form-control m-b" name="account"  v-model="editfieldId" id="editBlock">
                    <template v-for="item in todos">
                        <option value="{{item.id}}" >{{item.fieldName}}</option>
                    </template>
                </select>
            </div>
            <div>
                <span class="pull-left">字段类型</span>
                <select class="form-control m-b" name="account"  v-model="editFindfield" id="editFieldType">
                    <template v-for="itemType in itemTypeList">
                        <option value="{{itemType.value}}" >{{itemType.name}}</option>
                    </template>
                </select>
            </div>
            <div>
                <span class="pull-left">显示名称</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="请输入显示名称，最长10个字符" maxlength=10 value="{{fieldInfo.fieldShowName}}" id="editShowName">
            </div>
            <div v-if="editFindfield=='select'" class="selectTxt">
                <div class=""><i class="fa fa-delicious text-success cursor addBlock headBtn" @click="editAdd()"> 添加筛选项</i></div>
                <div class="clearB" v-for="item in list" style="margin-bottom:15px;overflow: hidden;">
                    <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__  selectEditTxt margin fl"
                           name="search"
                           placeholder="请输入显示名称，最长60个字符" maxlength=60 :value="item.name" :id="item.id"> <span
                        class="pull-left clearTxt" @click="editDel($index)">×</span>
                </div>
            </div>

            <div v-if="editFindfield=='checkbox'" class="selectTxt">
                <div class=""><i @click="Add(2)" class="fa fa-delicious text-success cursor addBlock headBtn"> 添加筛选项</i>
                </div>
                <div v-for="item in list">
                    <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__  checkboxEditTxt margin fl"
                           name="search"
                           placeholder="请输入显示名称，最长60个字符" maxlength=10 :value="item.name" :id="item.id"> <span class="pull-left clearTxt" @click="del(2,$index)">×</span>
                </div>
                <div class="clearB Example">
                    <span class="pull-left fl">例：</span>
                    <div class="selectList">
                        <div v-for="item in selectList">{{item}}<span @click="delSelectList($index)" @click="del(2,$index)">×</span></div>
                    </div>
                </div>
            </div>
            <div v-if="editFindfield=='input'">
                <span class="pull-left">输入提示</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="输入提示，最长10个字符" maxlength=10 id="inputEditText" value="{{enterPrompt}}">
            </div>
            <div v-if="editFindfield=='text'">
                <span class="pull-left">输入提示</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="输入提示，最长10个字符" maxlength=10 id="textEditText" value="{{enterPrompt}}">
            </div>

            <div class="posttio">
                <button type="button" class="btn btn-w-m btn-primary" @click="editBaocun()">保存</button>
                <button type="button" class="btn btn-w-m btn-default" @click="cancel()">取消</button>
            </div>
        </div>
        <!--添加字段-->
        <div class="addFieldTxt posiF positionCont">
            <h4>添加字段</h4>
            <div>
                <span class="pull-left">区块</span>
                <select class="form-control m-b" name="account" id="fieldPid">
                    <template v-for="item in todos">
                        <option value="{{item.id}}" >{{item.fieldName}}</option>
                    </template>
                </select>
            </div>
            <div>
                <span class="pull-left">字段类型</span>
                <select class="form-control m-b" name="account" v-model="fieldType">
                    <option value="0">请选择字段类型</option>
                    <option value="select">单选下拉框</option>
                    <option value="checkbox">多选下拉框</option>
                    <option value="input">单行文字</option>
                    <option value="text">多行文字</option>
                </select>
            </div>
            <div>
                <span class="pull-left">显示名称</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="请输入显示名称，最长10个字符" maxlength=10 id="showName">
            </div>
            <div v-if="fieldType=='select'" class="selectTxt">
                <div class=""><i @click="Add(1)" class="fa fa-delicious text-success cursor addBlock headBtn"> 添加筛选项</i>
                </div>
                <div v-for="item in selectTxt">
                    <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__  selectDTxt margin fl"
                           name="search" placeholder="请输入显示名称，最长60个字符" maxlength=60>
                    <span class="pull-left clearTxt" @click="del(1,$index)">×</span>
                </div>
                <div class="clearB Example">
                    <span class="pull-left">例：</span>
                    <select class="form-control m-b" name="account">
                        <option value="0">电脑</option>
                    </select>
                </div>
            </div>
            <div v-if="fieldType=='checkbox'" class="selectTxt">
                <div class=""><i @click="Add(2)" class="fa fa-delicious text-success cursor addBlock headBtn"> 添加筛选项</i>
                </div>
                <div v-for="item in checkboxTxt">
                    <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__  checkboxDTxt margin fl"
                           name="search"
                           placeholder="请输入显示名称，最长60个字符" maxlength=10> <span class="pull-left clearTxt" @click="del(2,$index)">×</span>
                </div>
                <div class="clearB Example">
                    <span class="pull-left fl">例：</span>
                    <div class="selectList">
                        <div v-for="item in selectList">{{item}}<span @click="delSelectList($index)" @click="del(2,$index)">×</span></div>
                    </div>
                </div>
            </div>
            <div v-if="fieldType=='input'">
                <span class="pull-left">输入提示</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="请输入显示名称，最长10个字符" maxlength=10 id="inputText">
            </div>
            <div v-if="fieldType=='text'">
                <span class="pull-left">输入提示</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="请输入显示名称，最长10个字符" maxlength=10 id="textText">
            </div>
            <div class="posttio">
                <button type="button" class="btn btn-w-m btn-primary" @click="addSaveField()">保存</button>
                <button type="button" class="btn btn-w-m btn-default" @click="cancel()">取消</button>
            </div>
        </div>
        <!--添加区块-->
        <div class="blockCont positionCont">
            <h4>{{addOrEditBlockTitle}}</h4>
            <div>
                <span class="pull-left">区块名称：</span>
                <input type="text" class="form-control input-sm __web-inspector-hide-shortcut__   " name="search"
                       placeholder="请填写区块名称" id="blockName">
                <input type="hidden" id="blockId">
            </div>
            <div class="posttio">
                <button type="button" class="btn btn-w-m btn-primary" @click="saveBlockButton()">保存</button>
                <button type="button" class="btn btn-w-m btn-default" @click="cancel()">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/jqueryui.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/businessTypeFieldSet.js"></script>
</html>