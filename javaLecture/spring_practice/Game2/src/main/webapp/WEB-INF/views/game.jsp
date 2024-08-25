
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게임</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/game.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/game.js"></script>
</head>
<body>
    <h1>지뢰찾기 게임</h1>
    <h2 id="currentPlayer">Current Player: ${currentPlayer}</h2>
    <table>
        <%
            int[][] board = (int[][]) request.getAttribute("board");
            int size = (Integer) request.getAttribute("size");
            for (int i = 0; i < size; i++) {
                out.print("<tr>");
                for (int j = 0; j < size; j++) {
                    out.print("<td></td>");
                }
                out.print("</tr>");
            }
        %>
    </table>
</body>
</html>
