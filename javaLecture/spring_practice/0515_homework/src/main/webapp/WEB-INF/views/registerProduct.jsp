<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 등록</title>
</head>
<body>
	<form action="/registerProduct" method="post">
		상품명: <input type="text" name="productName"><br> 
		가격: <input type="text" name="price"><br> 
		재고: <input type="text" name="stock"><br> 
		이미지 주소: <input type="text" name="imageUrl"><br> 
		<input type="submit" value="등록">
	</form>
</body>
</html>