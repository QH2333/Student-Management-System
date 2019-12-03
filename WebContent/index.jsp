<%@ page import="studhandler.SQLBridge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>学生信息管理系统 - 登录</title>
</head>
<body>
	<p>Hello From Tomcat!</p>
	<form action="Login" method="POST">
	学号：<input type="text"  name="st_no"></br>
	密码：<input type="password" name="st_pwd"></br>
	<input type="submit" value="登录">
	<% String data=(String)request.getAttribute("data");  %>
</form>
</body>
</html>