import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class DBHelper {

	static String url = "jdbc:mysql://localhost:3306/";
	static String usernameDB = "root";
	static String passwordDB = "12345678";

	static ArrayList<Book> selectName(String name) throws ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		ArrayList<Book> bookList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM newBooks.books WHERE name='" + name + "'");
			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt(1), //id
						resultSet.getString(2), //name
						resultSet.getString(3), //tags
						resultSet.getString(4),  //locate
						resultSet.getString(5)); //host
				bookList.add(book);
			}


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


		return bookList;
	}

	static  Integer checkAccount(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM newBooks.users WHERE name='" + username + "' AND password='" + password + "'");
			resultSet.first();
			return resultSet.getInt(1);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
