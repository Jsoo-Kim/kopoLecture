
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Game Over</title>
</head>
<body>
    <h1>Game Over</h1>
    <p>The game has ended. <strong>${player}</strong> has triggered the bomb!</p>
    <a href="${pageContext.request.contextPath}/">Play Again</a>
</body>
</html>
