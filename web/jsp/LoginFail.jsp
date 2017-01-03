<%@page import="utility.PageState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"  type="text/css"  href="/css/common.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="state"
	class = "action.business.StatesBean"
	scope = "session"></jsp:useBean>

<% if(state.getState() == PageState.USERNOTFOUND){ %>
	<h3>用户名不存在</h3>
<% } else if(state.getState() == PageState.WRONGPWD){%>
	<h3>密码错误</h3>
<% } else {%>
	<h3>err</h3>
<% } %>



<form action="login.user" method="get">
	<input class="btn" type="submit" value="重新登录" />
</form>

</body>
</html>