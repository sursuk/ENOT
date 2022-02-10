


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;



@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
		maxFileSize = 1024 * 1024 * 50,      // 50 MB
		maxRequestSize = 1024 * 1024 * 500   // 500 MB
)



public class addFile extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//обработка формы добавления фаила


		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String tags = request.getParameter("tags");
		int year = Integer.parseInt(request.getParameter("year"));

		String sql = "INSERT INTO newBooks.books (name, tags, locate, hostName, year, author) VALUES (?, ?, ?, ?, ?, ?)";
		HttpSession session = request.getSession();
		String fileName = "";

		String filePath = "/home/sursuk/Desktop/apache-tomcat-9.0.58/webapps/data/";

		Part filePart = request.getPart("file");
		fileName = filePart.getSubmittedFileName();
		filePart.write(filePath + fileName);


		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(DBHelper.url, DBHelper.usernameDB, DBHelper.passwordDB);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tags);
			preparedStatement.setString(3, filePath + fileName);
			preparedStatement.setInt(4, (Integer) session.getAttribute("id"));
			preparedStatement.setInt(5, year);
			preparedStatement.setString(6, author);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			request.setAttribute("outEx", e);
		} catch (ClassNotFoundException e) {
			request.setAttribute("outEx", e);
		} catch (InvocationTargetException e) {
			request.setAttribute("outEx", e);
		} catch (InstantiationException e) {
			request.setAttribute("outEx", e);
		} catch (IllegalAccessException e) {
			request.setAttribute("outEx", e);
		} catch (NoSuchMethodException e) {
			request.setAttribute("outEx", e);
		}
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
}
