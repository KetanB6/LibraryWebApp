<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Available Books</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body style="margin-top: 20px;">

        <div class="container">
        <div class="button-container options">
        <button onclick="history.back()" class="btn" style="width: 15vh; position:relative; right: 10px; ">Back</button> 
        	<h1>Borrower Page</h1>
        	<form action="BorrowBook2" method="post">
        		<div style="text-align: left;">
        			<h3>Member ID   : ${member_id}</h3>
        			<h3>For Days    : ${days}</h3>
        			<h4>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<font color="blue">Book Available</font></h4>
        			<h3>Book Id     : ${book_id}</h3>
        			<h3>Book Name   : ${name}</h3>
        			<h3>Book Author : ${author}</h3>
        			<h3>Book Genre  : ${genre}</h3>
        			<h3>Book Edition: ${edition}</h3>
        			<h3>Publisher   : ${publisher}</h3>
        			
        			<h4>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<font color="blue">Book Landed!</font></h4>
        		</div>
        	</form>
        	</div>
        </div>
  
</body>
</html>