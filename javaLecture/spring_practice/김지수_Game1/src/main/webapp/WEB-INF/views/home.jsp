<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/index.css"/>
    <title>메인</title>
</head>
<body>
    <a href="input_user">참여자 입력</a>
    <a href="game">게임 진행</a>
    
    <h4>현재 참여자 목록</h4>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>${user}</li>
        </c:forEach>
    </ul>
</body>
</html>
