<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet"  type="text/css"  href="/css/Login.css"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>登录</title>
</head>
<body>
<div class="container">
  <div id="login-title">
    <h1>测试成绩查询</h1>
  </div>

  <div  id="login-info">
    <form method="post" action="/login.user">
      <table id="login-table">
        <tr>
          <td>用户名:</td>
          <td>
            <input type='text' name='username' size=25 class="input-text">
          </td>
        </tr>
        <tr>
          <td>密码:</td>
          <td>
            <input type='password'name='password' size=25 class="input-text">
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" class="btn" value="登录">
          </td>
        </tr>
      </table>
    </form>

    <form method="get" action="/login.user">
      <input type="submit" value="游客登录" name="visitor" class='btn'>
    </form>
  </div>

</div>


</body>
</html>