package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBook() {
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
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");;
        String genre = request.getParameter("genre");
        String publisher = request.getParameter("publisher");
        String isbn = request.getParameter("isbn");
        try {
        int book_id = addCopy(name, author, isbn);
        if(book_id == 1){
            System.out.print("Generating Book Id");
            Thread.sleep(3000);
            book_id = bookIdGenerator();
        } else{
            while(isAvailable(book_id)){
                book_id += 1;
            }
        }

        String query = "INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, book_id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, author);
        preparedStatement.setString(4, edition);
        preparedStatement.setString(5, isbn);
        preparedStatement.setString(6, genre);
        preparedStatement.setString(7, publisher);
        preparedStatement.setString(8, "Available");
        int affected_rows = preparedStatement.executeUpdate();
        if(affected_rows > 0){
            System.out.println("Adding Book");
            Thread.sleep(3000);
            System.out.println("Book Added!");
            request.setAttribute("transection", "book_add_done");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registered.jsp");
			dispatcher.forward(request, response);
        } else{
            System.out.println("Error occurred while adding book!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
        }
        } catch(SQLException | InterruptedException e) {
        	System.out.println("SQL Exception: " + e);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
        } catch(Exception e) {
        	System.out.println("Seems Input Error: " + e);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
        }
	}
	
	public int addCopy(String name, String author, String isbn) throws SQLException{
        String query = "SELECT *FROM books WHERE book_name = ? and author = ? and isbn = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, author);
        preparedStatement.setString(3, isbn);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("book_id");
        }
        return 1;
    }
	
	private int bookIdGenerator() throws SQLException{
        String query = "SELECT *FROM books WHERE book_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        SecureRandom secureRandom = new SecureRandom();
        while (true) {
            int book_id = 100000 + (int)(secureRandom.nextDouble() * 900000);
            preparedStatement.setInt(1, book_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                preparedStatement.close();
                return book_id;
            }
        }
    }
	
	public boolean isAvailable(int book_id) throws SQLException{
        String query = "SELECT *FROM books WHERE book_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, book_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
