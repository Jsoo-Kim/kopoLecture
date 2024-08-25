<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>사용자 입력 화면</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/iu.css" />
    </head>
    <body>
        <form action="input_action" method="post">
            <input type="text" placeholder="사용자 1" name="user1" />
            <input type="text" placeholder="사용자 2" name="user2" />
            <input type="text" placeholder="사용자 3" name="user3" />
            <input type="text" placeholder="사용자 4" name="user4" />
            <input type="text" placeholder="사용자 5" name="user5" />
            <input type="text" placeholder="사용자 6" name="user6" />
            <input type="submit" value="입력 완료" />
        </form>
    </body>
</html>