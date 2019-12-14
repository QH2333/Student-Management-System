<%@ page import="studhandler.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="./style/weui.css"/>
    <link rel="stylesheet" href="./style/mystyle.css"/>
	<title>修改密码</title>
</head>
<body>
	<%  String st_no = request.getParameter("st_no");
	%>
	<br/>
	<div class="login-card">
		<h2 class="header">修改密码</h2>
		<br/>
		<form action="ChangePwd" method="POST">
		　学　号：<input type="text" name="st_no" value="<%out.print(st_no);%>" readonly class="chpwd-input"> <br/><br/>
		　原密码：<input type="password" name="old_pwd" class="chpwd-input"><br/><br/>
		　新密码：<input type="password" name="new_pwd" class="chpwd-input"><br/><br/>
		确认密码：<input type="password" name="repeat_pwd" class="chpwd-input"><br/><br/>
		<input type="submit" value="修改"  class="weui-btn weui-btn_primary">
		</form>
	</div>
</body>
</html>