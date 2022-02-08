


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//обработка формы авторизации

		int id = DBHelper.checkAccount(request.getParameter("username"), request.getParameter("password"));

		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		request.setAttribute("out", id);
		//}
		if(id == -10){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
			request.getRequestDispatcher("main.jsp").forward(request, response);
	}
}
