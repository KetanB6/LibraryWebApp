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

@WebServlet("/BorrowBook1")
public class BorrowBook1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BorrowBook1() {
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
		try {
			int member_id = member_id_for_session;
			String query = "SELECT *FROM members WHERE member_id = " + member_id + ";";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);
	        request.setAttribute("member_id", member_id);
	        String query2= "SELECT count(*) FROM borrower_list WHERE member_id = " + member_id + " and status='Borrowed';";
	        Statement statement1 = connection.createStatement();
	        ResultSet resultSet1 = statement1.executeQuery(query2);
	        if(resultSet1.next()){
	            int count = resultSet1.getInt(1);
	            if(count == 3){
	            	request.setAttribute("borrowed_3", "true");
	                System.out.println("Each Individual Member Can Borrow Maximum 3 Books!");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("only3.jsp");
	        		dispatcher.forward(request, response);
	                return;
	            }
	        }
	        int pin = 0;
	        if(resultSet.next()) {
	        	request.setAttribute("name", resultSet.getString("name"));
	        	request.setAttribute("mobile", resultSet.getString("mobile_no"));
	        	request.setAttribute("address", resultSet.getString("address"));
	            pin = resultSet.getInt("pin");
	        }
	        statement.close();
	        resultSet.close();
	        RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowerPage1.jsp");
    		dispatcher.forward(request, response);
	        
		} catch(SQLException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("only3.jsp");
    		dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
