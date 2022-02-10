

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ENOT extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//подключение к БД

		User user = null;

		try {

			HttpSession session = request.getSession();

			String search = request.getParameter("search");
			String from = request.getParameter("from");
			Boolean priv = true;
			int id;

			if(session.getAttribute("id") != null){
				id = (int) session.getAttribute("id");
			}
			else id = 0;

			if(request.getParameter("private") == null) {
				priv = false;
			}

			if(from == null){
				from = "name";
			}
			if(search == null){
				search = "";
			}

			switch (from){
				case "name": request.setAttribute("out", DBHelper.selectName(search, priv, id)); break;
				case "author": request.setAttribute("out", DBHelper.selectAuthor(search, priv, id)); break;
				case "tags": request.setAttribute("out", DBHelper.selectTags(search, priv, id)); break;
				case "year": request.setAttribute("out", DBHelper.selectYear(Integer.parseInt(search), priv, id)); break;
				default: request.setAttribute("out", DBHelper.selectName(search, priv, id)); break;
			}

			if(session.getAttribute("id") != null) {
				user = DBHelper.returnUser(id);
			}
			if(user != null) {
				request.setAttribute("outUser", user.getName());
			}

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

	}
}

