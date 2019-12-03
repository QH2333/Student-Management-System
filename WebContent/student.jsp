<%@ page import="studhandler.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
</head>
<body>
	<%  StudentInfo studInf = SQLBridge.getStudInfo(request.getParameter("st_no"));
		ArrayList<CourseInfo> allCourse = SQLBridge.getAllScore(studInf.studentNo);
	%>
	<p>你好，<%out.print(studInf.studentName); %>同学！</p>
	<table border="1">
	<tr>
		<td>课程代码</td>
		<td>课程名称</td>
		<td>你的分数</td>
	</tr>
	<%	for (CourseInfo item: allCourse) {
			out.println("<tr>");
			out.println(String.format("<td><a href=\"./coursedetail.jsp?c_no=%s&st_no=%s\">%s</a></td>", item.courseNo, studInf.studentNo, item.courseNo));
			out.println(String.format("<td>%s</td>", item.courseName));
			if (item.isFilled) out.println(String.format("<td>%.2f</td>", item.courseScore));
			else out.println("<td>0(成绩缺失)</td>");
			out.println("</tr>");
		}
	%>
	</table>
	
	<p>你的均分排名：<%out.print(SQLBridge.getAvgRank(studInf.studentNo));%>/<%out.print(SQLBridge.getStudCount());%></p>
	
	<a href="<%out.print(String.format("./chpwd.jsp?st_no=%s", studInf.studentNo));%>">修改密码</a>
</body>
</html>