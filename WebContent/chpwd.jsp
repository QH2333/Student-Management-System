<%@ page import="studhandler.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
</head>
<body>
	<%  String st_no = request.getParameter("st_no");
	%>
	<form action="ChangePwd" method="POST">
	学号：<input type="text" name="st_no" value="<%out.print(st_no);%>" readonly>
	新密码：<input type="password" name="st_pwd"></br>
	<input type="submit" value="修改">
	</form>
</body>
</html>