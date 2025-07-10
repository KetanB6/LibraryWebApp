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

@WebServlet("/NewMember")
public class NewMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NewMember() {
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
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        System.out.print("Library Deposit: Rs." + 500);
        int memberId = bookIdGenerator()*(2);
        System.out.println("\nMember ID: " + memberId);
        int pin = pinGenerator();
   
            String query = "INSERT INTO members VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mobile);
            preparedStatement.setInt(4, memberId);
            preparedStatement.setInt(5, pin);
            preparedStatement.setInt(6, 500);
            int affected_rows = preparedStatement.executeUpdate();
            if(affected_rows > 0) {
                System.out.println("Creating Member"); 
                System.out.println("Member created and only 3 books can be borrowed in week!");
                request.setAttribute("transection", "member_added");
                request.setAttribute("name", name);
                request.setAttribute("mobile", mobile);
                request.setAttribute("address", address);
                request.setAttribute("member_id", memberId);
                request.setAttribute("pin", pin);
                request.setAttribute("deposit", 500);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Member_Id_PIN.jsp");
        		dispatcher.forward(request, response);
            } else {
                System.out.println("Member Registration Failed!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianPanel.jsp");
        		dispatcher.forward(request, response);
            }
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int pinGenerator() throws SQLException{
        SecureRandom secureRandom = new SecureRandom();
        return 1000 + (int)(secureRandom.nextDouble() * 9000);
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
