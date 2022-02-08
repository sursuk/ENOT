


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
		//Integer id = DBHelper.checkAccount(request.getParameter("username"), request.getParameter("password"));
		//if (id != null) {
		int id = -10;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(DBHelper.url, DBHelper.usernameDB, DBHelper.passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM newBooks.users WHERE name='" + request.getParameter("username") + "' AND password='" + request.getParameter("password") + "'");
			resultSet.next();
			id =  resultSet.getInt(1);

		} catch (InstantiationException e) {
			request.setAttribute("outEx", e);
		} catch (IllegalAccessException e) {
			request.setAttribute("outEx", e);
		} catch (InvocationTargetException e) {
			request.setAttribute("outEx", e);
		} catch (NoSuchMethodException e) {
			request.setAttribute("outEx", e);
		} catch (ClassNotFoundException e) {
			request.setAttribute("outEx", e);
		} catch (SQLException e) {
			request.setAttribute("outEx", e);
		}


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
