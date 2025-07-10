package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AvailableBooks")
public class AvailableBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AvailableBooks() {
        super();
    }
    
    private static final String url = "jdbc:mysql://localhost:3306/LibrarySystem";
    private static final String username = "root";
    private static final String password = "data@ketan5678";
    
    private Connection connection;
    
    public void loadDrivers() {
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    System.out.println("Driver loaded successfully!");
    	    connection = DriverManager.getConnection(url, username, password);
    	} catch (ClassNotFoundException | SQLException e) {
    	    System.out.println("Driver not found!" + e);
    	    e.printStackTrace();
    	} 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loadDrivers();
		try {
			String query = "SELECT *FROM books;";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);
	        int sr = 1;
	        System.out.println("Retrieving Books Data");
	        List<String> rows = new ArrayList<String>();
			while(resultSet.next()){
			    String row = "|" + sr + "      |" + resultSet.getInt("book_id") + "  |" + resultSet.getString("book_name") + "  |" + resultSet.getString("author") + "  |" + resultSet.getString("edition") + "       |" + resultSet.getString("genre") + "  |" + resultSet.getString("isbn") + "  |" + resultSet.getString("publisher") + "  |" + resultSet.getString("status") + "  |"; 
			    rows.add(row);
			    sr++;
			}
			resultSet.close();
			statement.close();
			String[] books = rows.toArray(new String[0]);
			request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("showBooks.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
