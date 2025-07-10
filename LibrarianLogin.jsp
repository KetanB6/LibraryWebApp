<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Librarian Login</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body>
	<h2>Library Management System</h2>
        <div class="container">
        	<h1>Login</h1><br>
        	<form action="LibrarianLogin" method="post" class="button-container">
        		<input type="email" placeholder="Enter Email" name="email" required>
        		<input type="password" placeholder="Enter Password" name="password" required>
        		<button name="verify" class="btn">Login</button>
        	</form>
        </div>
</body>
</html>