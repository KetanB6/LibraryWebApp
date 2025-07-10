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

@WebServlet("/ListMembers")
public class ListMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListMembers() {
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
		String query = "SELECT *FROM members;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> rows = new ArrayList<String>();
        int sr = 1;
        while(resultSet.next()){
        	String row = "|" + sr + "      |" + resultSet.getString("name") + "  |" + resultSet.getString("address") + "  |" + resultSet.getString("mobile_no") + "  |" + resultSet.getInt("member_id") + "       |" + resultSet.getInt("pin") + "  |" + resultSet.getDouble("deposit") + "  |"; 
		    rows.add(row);
		    sr++;
        }
        resultSet.close();
        statement.close();
        String[] members = rows.toArray(new String[0]);
		request.setAttribute("books", members);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListMembers.jsp");
		dispatcher.forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception: " + e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
