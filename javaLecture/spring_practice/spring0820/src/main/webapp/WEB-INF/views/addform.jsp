<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game Participants</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
    <h1>참가자 등록</h1>
    
    <!-- 참가자 추가 폼 -->
    <form action="addParticipant" method="post">
        <label for="name">참가자 이름:</label>
        <input type="text" id="name" name="name" required>
        <button type="submit">등록</button>
    </form>

    <!-- 참가자 목록 보기 링크 -->
    <a href="participants">참가자 목록 보기</a>
</body>
</html>