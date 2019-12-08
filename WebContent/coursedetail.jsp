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
	<title>课程详情</title>
</head>
<body>
	<%  String c_no = request.getParameter("c_no");
		String st_no = request.getParameter("st_no");
	%>
	<br/>
    <div class="stud-card">
	课程均分：<%out.print(String.format("%.2f", SQLBridge.getCourseAvg(c_no))); %> <br/>
	你的排名：<%out.print(SQLBridge.getCourseRank(c_no, st_no)); %> <br/>
	</div>
</body>
</html>