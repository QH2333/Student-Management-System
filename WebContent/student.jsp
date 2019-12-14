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
    <title>学生信息管理系统</title>
</head>
<body>
    <br/>
    <div class="stud-card">
        <%  StudentInfo studInf = SQLBridge.getStudInfo(request.getParameter("st_no"));
            ArrayList<CourseInfo> allCourse = SQLBridge.getAllScore(studInf.studentNo);
        %>
        <p class="stud-com">你好，<b><%=studInf.studentName %></b>同学！</p>
        <br/>
        <p class="stud-com">你的<b>课程分数</b>：</p>
        <table id="table-course">
            <tr>
                <td>课程代码</td>
                <td>课程名称</td>
                <td>你的分数</td>
            </tr>
            <%	for (CourseInfo item: allCourse) {
                    out.println("<tr>");
                    out.println(String.format("<td><a class=\"stud-course\" href=\"./coursedetail.jsp?c_no=%s&st_no=%s\">%s</a></td>", item.courseNo, studInf.studentNo, item.courseNo));
                    out.println(String.format("<td>%s</td>", item.courseName));
                    if (item.isFilled) out.println(String.format("<td>%.2f</td>", item.courseScore));
                    else out.println("<td>0(成绩缺失)</td>");
                    out.println("</tr>");
                }
            %>
        </table>
        <br/>
        <p class="stud-com">你的<b>均分</b>：<%=SQLBridge.getAvg(studInf.studentNo) %>
        <p class="stud-com">你的<b>均分排名</b>：<%=SQLBridge.getAvgRank(studInf.studentNo) %> / <%=SQLBridge.getStudCount() %></p>
    </div>
    <br/>
    <p class="stud-bottom">©QH2333 · <a class="stud-rename" href="<%out.print(String.format("./chpwd.jsp?st_no=%s", studInf.studentNo));%>">修改密码</a></p>
</body>
</html>