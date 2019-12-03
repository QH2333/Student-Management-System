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
		if (studentNo == null || studentPwd == null) { // ��������			
			response.getWriter().append("[ERROR] Para");
		}
		else {
			int loginStatus = SQLBridge.Login(studentNo, studentPwd);
			switch (loginStatus) {
			case 0:
				//request.getRequestDispatcher("/admin.jsp").forward(request, response);
				response.sendRedirect("admin.jsp");
				break;
			case 1:
				//request.getRequestDispatcher("/student.jsp").forward(request, response);
				response.sendRedirect("student.jsp?st_no=" + studentNo);
				break;
			case -1:
				response.getWriter().append("��½����");
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
