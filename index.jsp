<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>
<link rel="stylesheet" href="styles/style2.css">
</head>
<body style="background: url('https://c4.wallpaperflare.com/wallpaper/479/101/113/germany-saxony-gorlitz-hall-historical-literature-wallpaper-preview.jpg'); background-repeat: no-repeat; background-size: cover; backdrop-filter: blur(15px);">
	<h2><font color="white">Library Management System</font></h2>
   
	<div class="container">
        <h1>Login</h1><br>
        <form action="MyServlet" method="post" class="button-container">
        	<button value="1" name="choice1" class="btn">Librarian</button>
        	<button value="2" name="choice1"class="btn">Member</button>
        </form>
    </div>
</body>
</html>