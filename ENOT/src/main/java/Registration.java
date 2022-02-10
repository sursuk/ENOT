import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.sql.*;

public class Registration extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("reg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
/*
		int id = DBHelper.checkAccount(username, password);

		if(id != -6 ){
			request.setAttribute("out", "arleady this account have");
		}
		else{
			id = DBHelper.addUser(username, password);
		}
*/
		int id;
		String sql = "INSERT INTO newBooks.users (name, password) VALUES (?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(DBHelper.url, DBHelper.usernameDB, DBHelper.passwordDB);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();

			id = DBHelper.checkAccount(username, password);
			request.setAttribute("out", id);

		} catch (InstantiationException e) {
			request.setAttribute("out", e);
		} catch (IllegalAccessException e) {
			request.setAttribute("out", e);
		} catch (InvocationTargetException e) {
			request.setAttribute("out", e);
		} catch (NoSuchMethodException e) {
			request.setAttribute("out", e);
		} catch (ClassNotFoundException e) {
			request.setAttribute("out", e);
		} catch (SQLException e) {
			request.setAttribute("out", e);
		}



	//	if(id == -10){
	//		request.getRequestDispatcher("reg.jsp").forward(request, response);
	//	}
	//	else
			request.getRequestDispatcher("/ENOT").forward(request, response);
	}
}
