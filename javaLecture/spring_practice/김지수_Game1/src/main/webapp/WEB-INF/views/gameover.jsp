<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게임 종료</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/gameover.css" />
    <script src="${pageContext.request.contextPath}/resources/js/gameover.js"></script>
</head>
<body>
    <div class="game-over-container">
        <h1>게임 종료!</h1>
        <h2>패배자: ${loser}</h2>
        <button id="restartButton">다시 시작하기</button>
    </div>
</body>
</html>
