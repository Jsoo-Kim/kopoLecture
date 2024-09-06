<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게임 설정 화면</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/iu.css" />
<!-- 제이쿼리 사용 시 제이쿼리 cdn 검색해서 공식홈에 있는 최신 버전 복붙하면 됨-->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/iu.js"></script>
<script>
	var contextPath = '${pageContext.request.contextPath}';
</script>
</head>
<body>
	<form id="userForm" action="input_action" method="post">
		<label for="playerCount">참여자 수:</label> <select id="playerCount"
			name="playerCount" required>
			<option value="2">2명</option>
			<option value="3">3명</option>
			<option value="4">4명</option>
			<option value="5">5명</option>
			<option value="6">6명</option>
			<option value="7">7명</option>
			<option value="8">8명</option>
			<option value="9">9명</option>
			<option value="10">10명</option>
		</select> <label for="mapSize">맵 크기:</label> <select id="mapSize"
			name="mapSize" required>
			<option value="3">3x3</option>
			<option value="4">4x4</option>
			<option value="5">5x5</option>
			<option value="6">6x6</option>
			<option value="7">7x7</option>
			<option value="8">8x8</option>
			<option value="9">9x9</option>
			<option value="10">10x10</option>
		</select>

		<div id="userInputs">
		</div>

		<input type="submit" value="입력 완료" />
	</form>

	<div id="message" style="display: none;">
		<p>입력 완료되었습니다!</p>
	</div>

	<div class="home-button-container">
		<button id="homeButton">홈으로 돌아가기</button>
	</div>

</body>
</html>
