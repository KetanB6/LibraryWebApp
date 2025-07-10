<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members Panel</title>
<link rel="stylesheet" href="styles/style2.css">
<style>
	.btn{
		width: 36vh;
	}
</style>
</head>
<body >
	
	<div class="overlay">
	<h2>Library Management System</h2>
        <div class="container options" id="0">
        	<h1>Members Panel</h1>
        		<div class="options">
	        		<button onclick="document.getElementById('1').style.display='flex'; document.getElementById('0').style.display='none';" class="btn">Search Book</button><br>
	        		<form action="BorrowBook1" method="post"><button class="btn">Borrow</button></form>
	        		<form action="ReturnBookServlet1" method="post"><button class="btn">Return Book</button></form>
	        		<form action="AvailableBooks" method="post"><button class="btn">Available Book</button></form>
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
        <h1>Search Book</h1> 
	        <form action="SearchBook" method="post">
        		<input type="text" placeholder="Book Name" name="book" required>
        		<input type="text" placeholder="Book Author" name="author" required><br>
        		<button name="verify" class="btn">Search</button>
        	</form>
        </div>
        </div>
        
            
        <div class="overlay centre" id="3" style="display: none;">
        	<h1>Return book</h1>        	
        	<form action="ReturnBookServlet1" method="post">
        		<button>Return</button>
        	</form>
        	<button onclick="main(3)">Back</button>   
        </div>
        
        <div class="overlay centre" id="4" style="display: none;">
        	<h1>Available Books</h1>        	
        	<form action="AvailableBooks" method="post">
        		<button>Get Books Data</button>
        	</form>   		
        	<button onclick="main(4)">Back</button>  
        </div>
        
     
    </div>
</body>
</html>