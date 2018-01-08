<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>选择部门</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/zTreeStyle/zTreeStyle.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/director.css" />
	</head>
	<body id="content" v-cloak>
		<div class="wrap">
			<p class="Title">请选择部门（可多选）</p>
			<div class="main">
				<div class="mainLeft">
					<p><i class="fa fa-search"></i><input type="text" placeholder="请输入部门名称查询" id="departmentName" class="search"/></p>
					<div class="selectAll">
						<p @click="searchCont($event,item.id,item.name)" v-for="item in department">{{item.name}}</p>
					</div>
					<div class="mainCont">
						<div v-for="item in peopleNum">
							{{item.name}}<span @click="removeList($index,item.id)">×</span>
						</div>
					</div>
				</div>
				<div class="mainRight">
					<p>请选择该部门（可多选）</p>
					 <div class="z_container">
				        <div class="nav">
				            <ol class="am-breadcrumb marginB0" style="position: relative;">
                                <input type="checkbox" id="0" style="position: absolute;right:10px;top:6px;width:16px;height:16px;" class="checkedAll">
				                <li class="am-active" id="{{departmentId}}">{{departmentName}}</li>
				            </ol>
				        </div>
				        <ul class="contactList department">
				
				        </ul>
				        <ul class="contactList person marginT20">
				
				        </ul>
				        <div class="remark" style="display:none;">暂无数据</div>
				    </div>
				</div>
			</div>
			<%--<div class="mainBtn">--%>
				<%--<div @click="Determine()">确定</div>--%>
				<%--<div @click="cancel()">取消</div>--%>
			<%--</div>--%>
		</div>
	</body>
</html>
<script src="${pageContext.request.contextPath}/static/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.ztree.core.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.ztree.excheck.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.ztree.exedit.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/ajax.session.time.out.js"></script>
<script src="${pageContext.request.contextPath}/static/src/js/MultipleDepartments.js"></script>
