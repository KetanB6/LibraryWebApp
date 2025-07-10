<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>
<link rel="stylesheet" href="styles/style2.css">
<style>
	.btn{
		width: 36vh;
	}
</style>
</head>
<body >	
	
        <div class="container" id="0">
        	
        		<div class="options">
        		<h1>Librarian Panel</h1>	
	        		<button onclick="document.getElementById('1').style.display='flex'; document.getElementById('0').style.display='none';" class="btn">Register New Librarian</button><br>
	        		<button onclick="document.getElementById('2').style.display='flex'; document.getElementById('0').style.display='none';" class="btn">Add Books</button><br>
	        		<form action="AvailableBooks" method="post" class="button-container">
	        		<button class="btn">Available Books</button>
	        		</form>
	        		<button onclick="document.getElementById('4').style.display='flex'; document.getElementById('0').style.display='none';" class="btn">Search Books</button><br>
	        		<button onclick="document.getElementById('5').style.display='flex'; document.getElementById('0').style.display='none';" class="btn">Register Member</button><br>
	        		<form action="ListMembers" method="post" class="button-container">
	        		<button class="btn">List Members</button>
	        		</form>
	        		<form action="Logout"><button onclick="alert('Logging out...')" class="btn">Logout</button><br></form>
        		</div>
        </div>
        
        <script>
        	function main(num){
	        	document.getElementById(num).style.display='none';
	        	document.getElementById('0').style.display='flex';
        	}
        </script>
        
        <div class="container" id="1" style="display: none;">
   
        <div class="button-container options" style="flex-wrap: wrap; width: 40vw;">
        <button onclick="main(1)" class="btn" style="width: 15vh; position:relative; right: 10px; ">Back</button> 
	        <form action="RegisterLibrarian" method="post">
	        <h1>Register New Librarian</h1> 
        		<input type="text" placeholder="Enter Full Name" name="name" required>
	        	<input type="email" placeholder="Enter email" name="email" required>
        		<input type="password" placeholder="Enter Password" name="password" required><br>
        		<button name="verify" class="btn">Register</button>
        	</form>
        	</div>
        
        </div>
        
        <div class="container" id="2" style="display: none;">
        	     	
        	 <div class="button-container options">
        	 <button onclick="main(2)" class="btn" style="width: 15vh; position: relative; right: 0px;">Back</button>    
	        <form action="AddBook" method="post">
	        <h1>Add Books</h1>   
        		<input type="text" placeholder="Book Name" name="name" required>
	        	<input type="text" placeholder="Author Name" name="author" required>
        		<input type="text" placeholder="Book Edition" name="edition" required>
        		<input type="text" placeholder="Book Genre" name="genre" required>
	        	<input type="text" placeholder="Book Publisher" name="publisher" required>
        		<input type="text" placeholder="ISBN" name="isbn" required><br>
        		<button name="verify" class="btn">Add Book</button>
        	</form>   		
        	</div>
        </div>
                
        <div class="container" id="4" style="display: none;">
         <div class="button-container options">
        <button onclick="main(4)" class="btn" style="width: 15vh; position: relative; right: 0px;">Back</button>
        	<h1>Search Books</h1>        	
        	<form action="SearchBookServlet" method="post">
        		<input type="text" placeholder="Book Name" name="book" required>
        		<input type="text" placeholder="Book Author" name="author" required><br>
        		<button name="verify" class="btn">Search</button>
        	</form>    		
        </div>
        </div>
        
        <div class="container" id="5" style="display: none;">
        <div class="button-container options" style="width: 50vw;">
        <button onclick="main(5)" class="btn" style="width: 15vh; position: relative; right: 0px;">Back</button>
        	<h1>Register Member</h1>        	
        	<form action="NewMember" method="post">
        		<input type="text" placeholder="Member Name" name="name" required>
	        	<input type="text" placeholder="Mobile Number" name="mobile" required>
        		<input type="text" placeholder="Address" name="address" required>
        		<input type="text" placeholder="Deposit" name="deposit" value="500">
        		<button name="verify" class="btn">Add Member</button>
        	</form>
        </div>
        </div>
        
        <div class="container" id="6" style="display: none;">
        	<h1>List Members</h1>        	
        	<form action="ListMembers" method="post" class="button-container">
        		<button class="btn">Get Member List</button>
        	</form>
        	<button onclick="main(6)" class="btn">Back</button>          	
        </div>
</body>
</html>