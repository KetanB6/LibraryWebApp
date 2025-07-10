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

@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LibrarianLogin() {
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
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		try {
			if(login(email, password)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianPanel.jsp");
    			dispatcher.forward(request, response);
    			int choice2 = Integer.parseInt(request.getParameter("choice2"));
    			System.out.println("choice2: " + choice2);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianLogin.jsp");
    			dispatcher.forward(request, response);
			}
			
		} catch(SQLException e) {
			System.out.println("SQL Exception: " + e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianLogin.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Seems Input Error: " + e);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianLogin.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean login(String email, String password) throws SQLException {
        String query = "SELECT * from librarians where email = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

}
