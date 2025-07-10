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
import java.sql.SQLException;

@WebServlet("/RegisterLibrarian")
public class RegisterLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterLibrarian() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
        String query = "INSERT INTO librarians VALUES(?, ?, ?);";
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        int affected_rows = preparedStatement.executeUpdate();
        if(affected_rows > 0){
            System.out.print("Registering Librarian");
            try {
				Thread.sleep(3000);
				request.setAttribute("transection", "registration_done");
			    System.out.println("Librarian Registered!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("registered.jsp");
    			dispatcher.forward(request, response);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
        } else{
            System.out.println("Registering Librarian");
            Thread.sleep(3000);
            System.out.println("Registration Failed!");
        }
        preparedStatement.close();
        } catch(SQLException e) {
        	System.out.println("SQL Exception: " + e);
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
