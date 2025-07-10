package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import static Servlets.MemberLogin.member_id_for_session;
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
import java.sql.Statement;

@WebServlet("/BorrowBook2")
public class BorrowBook2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BorrowBook2() {
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
			String isFound = "true";
			request.setAttribute("member_id", member_id);
			int book_id = searchByName_Author(request, response);
	        System.out.println();
	        if(book_id == 1){
	        	isFound = "false";
	            System.out.println("Book May Currently Not Available!");
	            request.setAttribute("isAvailable", isFound);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
        		dispatcher.forward(request, response);
	        }
	        System.out.print("For Days: ");
	        int days = Integer.parseInt(request.getParameter("days"));
	        request.setAttribute("days", days);
	           
	            if (confirmBorrowing(book_id, member_id_for_session, days)) {
	                System.out.println("Book Landed!");
	                request.setAttribute("isLanded", "true");
	                member_id_for_session = member_id;
	                RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowerPage2.jsp");
	        		dispatcher.forward(request, response);
	            } else {
	                System.out.println("Failed To Process Request!");
	                isFound = "false";
	                request.setAttribute("isAvailable", isFound);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
	        		dispatcher.forward(request, response);
	            }
	       
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int searchByName_Author(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = (String) request.getParameter("book_name");
        String author =(String) request.getParameter("author");
        System.out.println("name and author: " + name + " " + author); 
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
            	request.setAttribute("book_id", resultSet2.getInt("book_id"));
                return resultSet2.getInt("book_id");
            }
        } else {
            System.out.println("Book Not Found!");
            return 1;
        }
        return 1;
    }
	
	public boolean confirmBorrowing(int book_id, int member_id, int days) throws SQLException{
        String query = "INSERT INTO borrower_list(book_id, member_id, days, status) VALUES(?, ?, ?, ?);";
        String query2 = "UPDATE books SET status = 'Not Available' WHERE book_id = " + book_id + ";";
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        Statement statement = connection.createStatement();
        preparedStatement.setInt(1, book_id);
        preparedStatement.setInt(2, member_id);
        preparedStatement.setInt(3, days);
        preparedStatement.setString(4, "Borrowed");
        int affected_rows1 = preparedStatement.executeUpdate();
        int affected_rows2 = statement.executeUpdate(query2);
        preparedStatement.close();
        if(affected_rows1 > 0 && affected_rows2 > 0){
            connection.commit();
            return true;
        }
        connection.rollback();
        return false;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
