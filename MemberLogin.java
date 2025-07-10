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

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final String url = "jdbc:mysql://localhost:3306/LibrarySystem";
    private static final String username = "root";
    private static final String password = "data@ketan5678";
    
    protected static int member_id_for_session = 0;
    
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
		int member_id = Integer.parseInt(request.getParameter("memberid"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		System.out.println("Member id: " + member_id);
		System.out.println("PIN: " + pin);
		try {
		String query = "SELECT * FROM members WHERE member_id = ? and pin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, member_id);
        preparedStatement.setInt(2, pin);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
        	member_id_for_session = member_id;
        	request.setAttribute("login", "true");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("MembersPanel.jsp");
    		dispatcher.forward(request, response);
        } else {
        	request.setAttribute("login", "false");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("MemberLoginPage.jsp");
    		dispatcher.forward(request, response);
        }
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
