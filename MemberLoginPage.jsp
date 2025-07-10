<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Login</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body>
	<%
	    String login = (String) request.getAttribute("login");
	    if ("true".equals(login)) {
	%>
	    <script>
	        alert("Login Successfull!");
	    </script>
	<%
	    } else if("false".equals(login)){
	%>
		<script>
	        alert("Login Failed!");
	    </script>
	<%	
		}
	%>
	
	<h2>Library Management System</h2>
        <div class="container">
        	<h1>Member Login</h1><br>
        	<form action="MemberLogin" method="post" class="button-container">
        		<input type="text" placeholder="Enter Member Id" name="memberid" required>
        		<input type="password" placeholder="Enter PIN" name="pin" required>
        		<button name="verify" class="btn">Login</button>
        	</form>
        </div>
</body>
</html>