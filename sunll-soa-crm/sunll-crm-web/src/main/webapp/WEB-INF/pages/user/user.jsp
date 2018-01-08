<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/dist/font-awesome/css/font-awesome.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/src/css/user.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/layer/skin/layer.css" rel="stylesheet">
  <%--<link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">--%>
</head>
<body>
<div class="admin-content" id="content">
  <div class="admin-content-body am-cf">
    <!--标题-->
    <div class="am-text-lg centerTit">个人中心</div>
    <!--左边列表-->
    <ul class="am-fl leftList">
      <li style="color: red">基本信息</li>
      <%--<li>修改密码</li>--%>
    </ul>
    <!--右边的-->
    <form class="am-form rightForm" id="essentialInformation" novalidate="novalidate"  style="display:block;">
      <%--<fieldset>--%>
      <table class="am-table am-margin-top-xs">
        <tbody>
        <tr>
          <td class="tdFirst">手机号：</td>
          <td class="tdSecond">
            {{mobile}}
          </td>
        </tr>
        <tr>
          <td class="tdFirst">登录账号：</td>
          <td class="tdSecond"> {{mobile}} </td>
        </tr>
        <tr>
          <td class="tdFirst">姓&nbsp;&nbsp;名：</td>
          <td class="tdSecond">
            <input type="text" id="name" name="name" value="lin">
          </td>
        </tr>
        <tr>
          <td class="tdFirst">部门：</td>
          <td class="tdSecond">
            {{departmentNames}}
          </td>
        </tr>
        <tr>
          <td class="tdFirst">性别：</td>
          <td class="tdSecond">
            <label><input type="radio" class="am-text-top" @click = "radioChecked1()" value="1" name="sex" :checked="this.sex ==2?false:true" >男</label>
            <label><input type="radio" class="am-text-top" @click = "radioChecked2()" value="2" name="sex" :checked="this.sex ==1?false:true">女</label>
          </td>
        </tr>
        <tr>
          <td class="tdFirst">邮箱：</td>
          <td class="tdSecond">
            <input type="email" id="userEmail" name="userEmail" maxlength="20" placeholder="请输入您的邮箱" value="">
          </td>
        </tr>
        </tbody>
      </table>
      <a style="margin-left: 800px;background: grey;color: black;opacity: .6;padding: 6px;" @click="updateUserDetail()">保存个人信息</a>
    </form>
    <div></div>
  </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/dist/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/layer/layer.js"></script>
<%--<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>--%>
<script>
  var vm = new Vue({
    el: "#content",
    data: {
      mobile:"",
      departmentNames:[],
      sex:1,
    },
    methods: {
      updateUserDetail:function(){
        console.log("修改个人信息");
        var userId = sessionStorage.getItem("USER_TOKEN");//userId
        var userEmail = $("#userEmail").val();
        var sss = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        if(userEmail != null && userEmail != ""){
          if(!(sss.test(userEmail))){
            layer.msg("邮箱不合法，请重新输入");
            return false;
          }
        }
        var name = $("#name").val();
        $.ajax({
          url: "/unitAccountInterface/updateUserbyUserId",
          type: 'POST',
          async: false,
          data: {"userId":userId,"name":name,"userEmail":userEmail,"sex":vm.sex},
          dataType: "json",
          error: function () {
          },
          success: function (data) {
//            alert(1111)
            console.log(data);
//            layer.msg("修改个人信息成功！")
            if(data == 1){
//              alert(1111)
//              setTimeout(function(){
              layer.msg("修改个人信息成功！")
//              },1000)
            }
          }
        });
      },
      radioChecked1:function(){
        this.sex = 1
      },
      radioChecked2:function(){
        this.sex = 2
      }
    }
  });
  var list = document.getElementsByTagName("li");
  var formArr = document.getElementsByTagName("form")
  for(var i = 0; i < list.length;i++) {
    list[i].index = i;
    list[i].onclick = function(){
      for(var j = 0; j < list.length; j++) {
        list[j].style.color = '#ccc';
        list[this.index].style.color = 'red';
        formArr[j].style.display = 'none';
        formArr[this.index].style.display = 'block';

      }
    }
  }
  $(function(){
    var userId = sessionStorage.getItem("USER_TOKEN");//userId
    $.ajax({
      url: "/unitAccountInterface/getUserbyUserId",
      type: 'POST',
      async: false,
      data: {"userId":userId},
      dataType: "json",
      error: function () {
      },
      success: function (data) {
        console.log(data);
        vm.name=data.name;
        vm.mobile=data.mobile;
        vm.sex=data.sex;
        vm.userEmail=data.userEmail;
        $("#name").val(data.name);
        $("#userEmail").val(data.userEmail);
      }
    });
    $.ajax({
      url: "/unitAccountInterface/getDepartmentListbyUserId",
      type: 'POST',
      async: false,
      data: {"userId":sessionStorage.getItem("USER_TOKEN"),"companyId":sessionStorage.getItem("CRM_COMPANY_TOKEN")},
      dataType: "json",
      error: function () {
      },
      success: function (data) {
        for(var i=0;i<data.length;i++){
          vm.departmentNames.push(data[i].name);
        }
      }
    });
  })
</script>
</html>