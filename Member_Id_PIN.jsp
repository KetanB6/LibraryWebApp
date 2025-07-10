<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Information</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body>
	
	<h2>Library Management System</h2>
        <div class="container">
        	<h1>Member Registered!</h1>
        	<div style="text-align: left;">
        	<h4>Member Name  &nbsp&nbsp&nbsp: ${name}</h4>
        	<h4>Member Mobile &nbsp&nbsp: ${mobile} </h4>
        	<h4>Member Address : ${address}</h4>
        	<h4>Member Id &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: ${member_id}</h4>
        	<h4>Member PIN &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: ${pin}</h4>
        	<h4>Member Deposit : ${deposit}</h4>
        	</div><br>
        	<button onclick="history.back()" class="btn">Back</button>
        </div>
 
</body>
</html>