

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ENOT extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//подключение к БД
		String search = (String) request.getAttribute("search");
		try {
			request.setAttribute("out", DBHelper.selectName(search));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}


		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//обработка формы авторизации
			Integer id = DBHelper.checkAccount(request.getParameter("username"), request.getParameter("password"));
			//if (id != null) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
			//}
	}
}

