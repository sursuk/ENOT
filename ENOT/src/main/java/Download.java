

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet{

	private static final int BYTES_DOWNLOAD = 1024*500;

	public void doGet(HttpServletRequest request,
	                  HttpServletResponse response) throws IOException{
		// тип данных, которые вы отправляете
		// например application/pdf, text/plain, text/html, image/jpg

		String name = request.getParameter("name");
		ArrayList<Book> list = new ArrayList<>();
		try {
			list = DBHelper.selectName(name, false, 2);
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

		String filePath = "/home/sursuk/Desktop/apache-tomcat-9.0.58/webapps/data/";
		String fileName = list.get(0).getLocate();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","attachment;");

		// файл, который вы отправляете
		File my_file = new File(fileName);

		// отправить файл в response
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(my_file);

		byte[] buffer = new byte[4096];
		int length;

		while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}

		// освободить ресурсы
		in.close();
		out.flush();
	}
}