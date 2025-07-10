<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Books</title>
    <link rel="stylesheet" href="styles/style2.css">
</head>
<body>
    <div class="container">
        <h1>Available Books</h1>
        <div class="books-list">
        <p>|SR NO. &nbsp&nbsp |BOOK ID &nbsp&nbsp |NAME &nbsp&nbsp |AUTHOR  &nbsp&nbsp |EDITION &nbsp&nbsp |GENRE &nbsp&nbsp |ISBN &nbsp&nbsp |PUBLISHER &nbsp&nbsp |status &nbsp&nbsp |</p>
        </div>
        <div class="books-list">
            <%
                String[] rows = (String[]) request.getAttribute("books");
                if (rows != null) {
                    for (String row : rows) {
            %>
                        <div class="book-item">
                            <p><%= row %> &nbsp&nbsp&nbsp&nbsp </p>
                        </div>
            <%
                    }
                } else {
            %>
                <p>No books available at the moment.</p>
            <%
                }
            %>
        </div>

        <button class="btn" onclick="history.back()">Go Back</button>
    </div>
</body>
</html>
