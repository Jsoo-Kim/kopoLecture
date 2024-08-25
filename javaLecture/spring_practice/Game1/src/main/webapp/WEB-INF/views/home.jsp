<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/index.css"/>
    <title>메인</title>
</head>
<body>
    <a href="input_user">사용자 입력</a>
    <a href="game">게임 진행</a>
    
    <h4>현재 사용자 목록</h4>
    <ul>
    	<li>${user1}</li>
    	<li>${user2}</li>
    	<li>${user3}</li>
    	<li>${user4}</li>
    	<li>${user5}</li>
    	<li>${user6}</li>
    </ul>
</body>
</html>
