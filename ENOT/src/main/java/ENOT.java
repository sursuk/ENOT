

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class ENOT extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//подключение к БД

		try {

			String search = request.getParameter("search");
			request.setAttribute("out", DBHelper.selectName(search));



		} catch (ClassNotFoundException e) {
			request.setAttribute("outEx", e);
		} catch (SQLException e) {
			request.setAttribute("outEx", e);
		} catch (NoSuchMethodException e) {
			request.setAttribute("outEx", e);
		} catch (InvocationTargetException e) {
			request.setAttribute("outEx", e);
		} catch (InstantiationException e) {
			request.setAttribute("outEx", e);
		} catch (IllegalAccessException e) {
			request.setAttribute("outEx", e);
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

