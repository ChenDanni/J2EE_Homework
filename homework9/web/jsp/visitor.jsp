<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/1/3
  Time: 下午4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet"  type="text/css"  href="/css/common.css"/>
    <title>游客登录</title>
</head>
<body>
<h3>游客登录</h3>
<br />

<s:form method="post" action="login">
    <s:submit value="注销" name="visitor-out" class="btn"/>
</s:form>
</body>
</html>
