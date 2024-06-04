<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.wrap {
	width: 500px;
	padding: 10px;
	border: 1px solid #999;
	margin: 20px auto;
	border-radius: 10px;
	background: #f1f1f1;
}

h1 {
	margin-bottom: 40px;
	font-size: 1.2em;
}

form {
	background: #fff;
	padding: 10px;
	border-radius: 10px;
	box-sizing: border-box;
}

input {
	width: 100%;
	line-height: 2em;
	height: 3em;
	box-sizing: border-box;
	padding-left: 10px;
	margin: 10px 5px;
}

p:hover {
	background: 8e93c0;
}

p:hover a {
	color: gray;
}

p {
	background: #fff;
	padding: 10px;
	border-radius: 10px;
	text-align: center;
}

ul, li {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
}

li {
	display: inline-block;
	padding: 10px 30px;
	border: 1px solid #333;
	border-radius: 5px;
}

li:hover {
	background: 8e93c0;
}

li:hover a {
	color: #ffffff;
}

a {
	text-decoration: none;
	color: #333;
}

a:hover {
	color: #ffffff;
}

div {
	text-align: center;
}

input[type="radio"] {
	margin-left: 20px;
	width: auto;
	height: auto;
}

</style>
</head>
<body>
	<div class="wrap">
		<h1>회원가입</h1>

		<nav>
			<ul>
				<!-- 홈 링크 -->
				<li><a href="/Customer/">홈</a></li>
			</ul>
		</nav>
		<form action="sign_in_action">
			<input type="text" name="id" placeholder="아이디" /><br>
			<input type="password" name="pw" placeholder="비밀번호" /><br>
			<input type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>