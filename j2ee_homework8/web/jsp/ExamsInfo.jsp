<%@page import="utility.PageState"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="MyTag" uri="/WEB-INF/tlds/MyTag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"  type="text/css"  href="/css/ExamsInfo.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测验查询结果</title>
</head>
<body>
<MyTag:CheckSession/>
<jsp:useBean id="examsInfo"
	type = "action.business.ExamsInfoBean"
	scope = "session"></jsp:useBean>
<jsp:useBean id="item"
	class = "action.business.ExamInfoVO"
	scope = "page"></jsp:useBean>
<jsp:useBean id="statistics"
	class = "action.business.StatisticsBean"
	scope = "session"></jsp:useBean>
<jsp:useBean id="states"
	class = "action.business.StatesBean"
	scope = "session"></jsp:useBean>

<div class="container">
	<div class="context">
		<h1>测试结果</h1>
		<% if(states.getState() == PageState.WARNING){ %>
		<h3 id="warning">警告，有未完成测试</h3>
		<% } %>

		<table id="exams-info-table">
			<tbody>
			<tr>
				<td>课程</td>
				<td>测试</td>
				<td>状态</td>
				<td>分数</td>
			</tr>
			<%
				for (int i = 0;i < examsInfo.getExamsInfoList().size();i++){
					pageContext.setAttribute("item", examsInfo.getExamsInfoList(i));
			%>
			<tr>
				<td><jsp:getProperty name="item" property = "lesson" /></td>
				<td><jsp:getProperty name="item" property = "test_name" /></td>
				<td><jsp:getProperty name="item" property = "state" /></td>
				<td><jsp:getProperty name="item" property = "score" /></td>
			</tr>
			<% } %>
			</tbody>
		</table>
		<br>

		<div id="statistics">
			<p class="number-info">在线人数：<jsp:getProperty name="statistics" property = "total" /></p>
			<p class="number-info">登录人数：<jsp:getProperty name="statistics" property = "login" /></p>
			<p class="number-info">游客人数：<jsp:getProperty name="statistics" property = "visitor" /></p>
		</div>

		<form method = "get" action = "/login.user">
			<input class="btn" type = 'submit' value = '注销' name = 'logout'>
		</form>
	</div>

</div>



</body>
</html>