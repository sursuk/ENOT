import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class DBHelper {

	static String url = "jdbc:mysql://localhost:3306/";
	static String usernameDB = "root";
	static String passwordDB = "12345678";

	static ArrayList<Book> selectName(String name) throws ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		String sql;
		ArrayList<Book> bookList = new ArrayList<>();

		if(name.equals("") || name.equals(" ")){
			sql = "SELECT * FROM newBooks.books";
		}
			else {
			sql = "SELECT * FROM newBooks.books WHERE name='" + name + "'";
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt(1), //id
						resultSet.getString(2), //name
						resultSet.getString(3), //tags
						resultSet.getString(4),  //locate
						resultSet.getInt(5),  //host
						resultSet.getInt(6),  //year
						resultSet.getString(7)); //author
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

	static ArrayList<Book> selectHost(int id){

		String sql = "SELECT * FROM newBooks.books WHERE hostName='" + id + "'";
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt(1), //id
						resultSet.getString(2), //name
						resultSet.getString(3), //tags
						resultSet.getString(4),  //locate
						resultSet.getInt(5),  //host
						resultSet.getInt(6),  //year
						resultSet.getString(7)); //author
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


	//*************************************************************
	//работа с Юзерами
	//**************************************************************
	static  Integer checkAccount(String username, String password) {
		int id = -10;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM newBooks.users WHERE name='" + username + "' AND password='" + password + "'");
			resultSet.next();
			id = resultSet.getInt(1);

		} catch (InstantiationException e) {
			return -1;
		} catch (IllegalAccessException e) {
			return -2;
		} catch (InvocationTargetException e) {
			return -3;
		} catch (NoSuchMethodException e) {
			return -4;
		} catch (ClassNotFoundException e) {
			return -5;
		} catch (SQLException e) {
			return -6;
		}
		return id;
	}

	static User returnUser(int id){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM newBooks.users WHERE id='" + id + "'");
			resultSet.next();
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			return user;
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

	static Integer addUser(String username, String password){
		int id;
		String sql = "INSERT INTO newBooks.users (name, password) VALUES (?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();

			id = checkAccount(username, password);

		} catch (InstantiationException e) {
			return -1;
		} catch (IllegalAccessException e) {
			return -2;
		} catch (InvocationTargetException e) {
			return -3;
		} catch (NoSuchMethodException e) {
			return -4;
		} catch (ClassNotFoundException e) {
			return -5;
		} catch (SQLException e) {
			return -6;
		}
		return id;
	}
}
