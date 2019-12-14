package studhandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePwd
 */
@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentNo = request.getParameter("st_no");
		String oldPwd = request.getParameter("old_pwd");
		String newPwd = request.getParameter("new_pwd");
		String repeatPwd = request.getParameter("repeat_pwd");
		if (studentNo == null || oldPwd == null || newPwd == null || repeatPwd == null) { // 参数错误			
			response.getWriter().append("[ERROR] Para");
		}
		if (!newPwd.equals(repeatPwd)) { // 两次输入新密码不匹配
			response.sendRedirect("chpwd.jsp?login_error=2&st_no=" + studentNo);
			return;
		}
		else if (SQLBridge.Login(studentNo, oldPwd) == -1) { // 老密码错误
			response.sendRedirect("chpwd.jsp?login_error=1&st_no=" + studentNo);
			return;
		}
		else {
			SQLBridge.chpwd(studentNo, newPwd);
			response.sendRedirect("index.jsp");
			return;
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
