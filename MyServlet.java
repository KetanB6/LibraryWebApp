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
import java.sql.SQLException;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	private static final String url = "jdbc:mysql://localhost:3306/LibrarySystem";
    private static final String username = "root";
    private static final String password = "data@ketan5678";
    
    public MyServlet() {
        super();
    }
    
    public static void loader() throws InterruptedException{
        int i = 3;
        while(i != 0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
    }
    
    public static void loadDrivers() {
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    System.out.println("Driver loaded successfully!");
    	} catch (ClassNotFoundException e) {
    	    System.out.println("Driver not found!");
    	    e.printStackTrace();
    	}

    }
    
    public void executeChoice1(int choice1) throws InterruptedException, ServletException, IOException {
    	try {
	    	Connection connection = DriverManager.getConnection(url, username, password);
	    	System.out.println("Connection loaded successfully!");
	    	
	    	while(true) {
	            int choice = choice1;
	            
	            if (choice == 1) {
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianLogin.jsp");
	    			dispatcher.forward(request, response);
	            } else if(choice == 2) {
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("MemberLoginPage.jsp");
	    			dispatcher.forward(request, response);
	            }
	    	}
	    	
	    	
	    	} catch(SQLException e) {
	    		System.out.println("Sql Exception: " + e);
	    	}
        
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loadDrivers();
	    this.request = request;
	    this.response = response;
		int choice1 = Integer.parseInt(request.getParameter("choice1"));
		try {
			executeChoice1(choice1);
		} catch (InterruptedException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
