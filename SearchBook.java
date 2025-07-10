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

@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SearchBook() {
        super();
        // TODO Auto-generated constructor stub
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
        String name = request.getParameter("book");
        String author = request.getParameter("author");
        String query = "SELECT *FROM books WHERE book_name = ? and author = ?;";
        String query2 = "SELECT *FROM books WHERE book_name = ? and author = ? and status = 'available';";
        String Name = "";
        String Author = "";
        int book_id = 0;
        String Genre = "";
        String Edition = "";
        String isbn = "";
        String publisher = "";
        int copies = 0;
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, author);
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setString(1, name);
        preparedStatement2.setString(2, author);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        if(resultSet.next()) {
            if(resultSet.getString("status").equalsIgnoreCase("available")) {
                copies++;
            }
            book_id = resultSet.getInt("book_id");
            Name = resultSet.getString("book_name");
            Author = resultSet.getString("author");
            Genre = resultSet.getString("genre");
            Edition = resultSet.getString("edition");
            isbn = resultSet.getString("isbn");
            publisher = resultSet.getString("publisher");
            while (resultSet.next()) {
                if(resultSet.getString("status").equalsIgnoreCase("available")) {
                    copies++;
                }
            }
            request.setAttribute("name", Name);
            request.setAttribute("author", Author);
            request.setAttribute("genre", Genre);
            request.setAttribute("edition", Edition);
            request.setAttribute("isbn", isbn);
            request.setAttribute("publisher", publisher);
            request.setAttribute("copies", copies);
            
            
            if(resultSet2.next()){
            	RequestDispatcher dispatcher = request.getRequestDispatcher("showAvailableBooks.jsp");
        		dispatcher.forward(request, response);
            } else {
            	request.setAttribute("isAvailable", "false");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
        		dispatcher.forward(request, response);
            }
            
        } else {
        	request.setAttribute("isAvailable", "false");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
    		dispatcher.forward(request, response);
        }
        }catch(SQLException e) {
        	e.getStackTrace();
        }
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
