<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>게임 화면</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/game.css" /> 
    <!-- 제이쿼리 사용 시 제이쿼리 cdn 검색해서 공식홈에 있는 최신 버전 복붙하면 됨-->
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/game.js"></script>
</head>
<body>
    <h2>현재 턴: ${users[turn]}</h2>
    <table>
        <c:forEach var="i" begin="0" end="${mapSize - 1}">
            <tr>
                <c:forEach var="j" begin="0" end="${mapSize - 1}">
                    <td data-y="${i}" data-x="${j}"></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    
    <audio id="explosion-sound" src="${pageContext.request.contextPath}/resources/sounds/explosion.mp3" preload="auto"></audio>
</body>
</html>
