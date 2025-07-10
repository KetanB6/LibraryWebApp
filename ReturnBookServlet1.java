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

import static Servlets.MemberLogin.member_id_for_session;

@WebServlet("/ReturnBookServlet1")
public class ReturnBookServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReturnBookServlet1() {
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
		int member_id = member_id_for_session;
		System.out.println("mid: " + member_id);
		try {
		String query = "SELECT *FROM members WHERE member_id = " + member_id + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
    
        if(resultSet.next()) {
        	request.setAttribute("member_id", member_id);
        	request.setAttribute("name", resultSet.getString("name"));
        	request.setAttribute("mobile", resultSet.getString("mobile_no"));
        	request.setAttribute("address", resultSet.getString("address"));
        	RequestDispatcher dispatcher = request.getRequestDispatcher("ReturnPage1.jsp");
    		dispatcher.forward(request, response);
        } else {
        	System.out.println("member not found!");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
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
