<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="MyTag" uri="/WEB-INF/tlds/MyTag.tld" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"  type="text/css"  href="/css/Login.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<MyTag:CheckSession/>
	<div class="container">
		<div id="login-title">
			<h1>测试成绩查询</h1>
		</div>

		<div  id="login-info">
			<s:form action="login" method="POST">
				<table id="login-table">
					<tr>
						<td>
							<s:textfield name="username" class="input-text" label="用户名"/>
							<%--<input type='text' name='username' size=25 class="input-text">--%>
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield name="password" class="input-text" label="密码"/>
							<%--<input type='password'name='password' size=25 class="input-text">--%>
						</td>
					</tr>
					<tr>
						<td>
							<s:submit value="登录" class="btn"/>
							<%--<input type="submit" class="btn" value="登录">--%>
						</td>
					</tr>
				</table>
			</s:form>


			<s:form action="login" method="POST">
				<s:submit value="游客登录" name="visitor" class="btn"/>
			</s:form>

		</div>

	</div>

</body>
</html>