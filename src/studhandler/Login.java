package studhandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentNo = request.getParameter("st_no");
		String studentPwd = request.getParameter("st_pwd");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		if (studentNo == null || studentPwd == null) { // 参数错误			
			response.getWriter().append("[ERROR] Para");
		}
		else {
			int loginStatus = SQLBridge.Login(studentNo, studentPwd);
			switch (loginStatus) {
			case 0:
				response.sendRedirect("admin.jsp"); // 跳转到管理员界面
				break;
			case 1:
				response.sendRedirect("student.jsp?login_status=1&st_no=" + studentNo); // 跳转到学生界面
				break;
			case -1: // 登录错误
				response.sendRedirect("index.jsp?login_error=1");
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
