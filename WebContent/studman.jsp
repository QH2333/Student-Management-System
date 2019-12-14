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
    <title>学生信息管理系统 - 管理员</title>
</head>
<body>
    <%  ArrayList<StudentInfo> allStud = SQLBridge.getAllStud(); %>
    <br/>
    <div class="admin-card">
        <p class="stud-com">管理员好！</p>
        <br/>
        <p class="stud-com">学生表单：</p>
        <table id="table-course">
            <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>密码</td>
                <td>操作</td>
            </tr>
            <%	for (StudentInfo item: allStud) {
                    out.println("<tr>");
                    out.println(String.format("<td>%s</td>", item.studentNo));
                    out.println(String.format("<td>%s</td>", item.studentName));
                    if (item.gender == 1) out.println("<td>男</td>");
                    else if (item.gender == 2) out.println("<td>女</td>");
                    else out.println("<td>null</td>");
                    out.println(String.format("<td>%s</td>", item.studentPwd));
                    out.println(String.format("<td><a class=\"admin_mod\" href=\"./studmod.jsp?st_no=%s\">修改</a></td>", item.studentNo));
                    out.println("</tr>");
                }
            %>
        </table>
        <br/>
        <button class="weui-btn weui-btn_primary" type="button" onclick="window.location.href='studadd.jsp'">添加学生</button>
        <br/>
    </div>
    <br/>
</body>
</html>