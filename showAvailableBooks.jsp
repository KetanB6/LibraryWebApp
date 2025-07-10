<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Books</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body>
	
	<h2>Library Management System</h2>
        <div class="container">
        	<h1>Available Books</h1>
        	<div style="text-align: left;">
        		<h3>Book Name: ${name}</h3>
        		<h3>Book Author: ${author}</h3>
        		<h3>Book Name: ${genre}</h3>
        		<h3>Book Edition: ${edition}</h3>
        		<h3>Book ISBN: ${isbn}</h3>
        		<h3>Book Publisher: ${publisher}</h3>
        		<h3>Available Copies : ${copies}</h3>
        	</div><br>
        	<button onclick="history.back()" class="btn" style="width: 15vh; position:relative; right: 10px; ">Back</button> 
        </div>
  
</body>
</html>