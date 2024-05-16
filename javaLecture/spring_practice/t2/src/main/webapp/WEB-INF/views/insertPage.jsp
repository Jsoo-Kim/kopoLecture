<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 입력</title>
<style type="text/css">
input {
	width: 100%;
	margin: 10px 0px;
	line-height: 3em;
}
</style>
</head>
<body>
	<form action='insertAction' method='GET'>
		<input type="text" name="t1" placeholder="이름 입력" /> 
		<input type="number" name="s1" placeholder="성적 입력" /> 
		<input type="submit" value="입력 완료" />
	</form>
</body>
</html>