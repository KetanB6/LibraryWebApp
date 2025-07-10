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
        			<h3>Member ID &nbsp&nbsp&nbsp&nbsp&nbsp  : ${member_id}</h3>
        			<h3>Member Name : ${name}</h3>
        			<h3>Mobile No.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp : ${mobile}</h3>
        			<h3>Address  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp : ${address}</h3> 
        			<h3>Book Id   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp : <input type="text" name="book_id"></h3>
        		</div><br>
        		<button class="btn">Return</button>
        	</form>
        </div>
	</div>
</body>
</html>