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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SearchBookServlet() {
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
        String name = request.getParameter("book");
        String author = request.getParameter("author");
        String query = "SELECT *FROM books WHERE book_name = ? and author = ?;";
        int sr = 1;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, author);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> rows = new ArrayList<>();
        while (resultSet.next()) {
        	String row = "|" + sr + "      |" + resultSet.getInt("book_id") + "  |" + resultSet.getString("book_name") + "  |" + resultSet.getString("author") + "  |" + resultSet.getString("edition") + "       |" + resultSet.getString("genre") + "  |" + resultSet.getString("isbn") + "  |" + resultSet.getString("publisher") + "  |" + resultSet.getString("status") + "  |"; 
		    rows.add(row);
		    sr++;
        }
        String[] books = rows.toArray(new String[0]);
		request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showBooks.jsp");
		dispatcher.forward(request, response);
    
		} catch(SQLException e) {
		System.out.println("SQL Exception: " + e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
