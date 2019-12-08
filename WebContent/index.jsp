<%@ page import="studhandler.SQLBridge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./style/weui.css"/>
<link rel="stylesheet" href="./style/mystyle.css"/>
<title>学生信息管理系统 - 登录</title>
</head>
<body>
    <br/>
    <div class="login-card">
        <h2 class="header">学生信息管理系统</h2>
        <br/>
        <form action="Login" method="POST">
            <div class="login-wrapper">
                学号：<input type="text" name="st_no" class="login-input"><br/>
            </div>
            <div class="login-wrapper">
                密码：<input type="password" name="st_pwd" class="login-input"><br/>
            </div>
            <br/>
            <input type="submit" value="登录" class="weui-btn weui-btn_primary">
            <% String data=(String)request.getAttribute("data");  %>
        </form>
    </div>

</body>
</html>