<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Return Book</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body>

        <div class="container">
        <div class="button-container options">
        <button onclick="history.back()" class="btn" style="width: 15vh; position:relative; right: 10px; ">Back</button> 
        	<h1>Return Page</h1>
        	<form action="ReturnBookServlet2" method="post">
        		<div style="text-align: left;">
        			
      
        			<h3>Book Id     : ${book_id}</h3>
        			<h3>Book Name   : ${book_name}</h3>
        			<h3>Book Author : ${author}</h3><br><br>
        			
        			<h4>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<font color="blue">Book Returned!</font></h4>
        		</div>
        	</form>
        </div>
    </div>
</body>
</html>