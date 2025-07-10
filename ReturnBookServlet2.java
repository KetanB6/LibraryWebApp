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

@WebServlet("/ReturnBookServlet2")
public class ReturnBookServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReturnBookServlet2() {
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
		
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        request.setAttribute("book_id", book_id);

        String query2 = "SELECT *FROM borrower_list WHERE book_id = " + book_id + " and member_id = " + member_id + " and status = 'Borrowed';";
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery(query2);
        if(!resultSet1.next()){
        	request.setAttribute("isAvailable", "false");
            System.out.println("No such book borrowed by member!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
    		dispatcher.forward(request, response);
            return;
        }

        String query3 = "SELECT book_name, author FROM books WHERE book_id = " + book_id + ";";
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery(query3);
        if(resultSet2.next()){
            request.setAttribute("book_name", resultSet2.getString("book_name"));
            request.setAttribute("author", resultSet2.getString("author"));
        } else{
            System.out.println("Invalid Book ID!");
            request.setAttribute("isAvailable", "false");
            System.out.println("No such book borrowed by member!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
    		dispatcher.forward(request, response);
        }

        String query4 = "UPDATE books SET status = 'Available' WHERE book_id = " + book_id + ";";
        String query5 = "UPDATE borrower_list SET status = 'Returned' WHERE book_id = " + book_id + " and member_id = " + member_id + ";";
        Statement statement3 = connection.createStatement();
        statement3.addBatch(query4);
        statement3.addBatch(query5);
        int[] arr = statement3.executeBatch();
        System.out.print("Processing Your Request");
        if(arr[0] > 0 && arr[1] > 0){
            System.out.println("Book Returned!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ReturnPage2.jsp");
    		dispatcher.forward(request, response);
        } else {
            System.out.println("Book Return Failed!");
            request.setAttribute("returnFailed", "fail");
            System.out.println("No such book borrowed by member!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
    		dispatcher.forward(request, response);
        }
		} catch(SQLException e) {
			e.getStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
