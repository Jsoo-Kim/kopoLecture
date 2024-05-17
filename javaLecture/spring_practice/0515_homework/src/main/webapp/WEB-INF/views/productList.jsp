
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<h1>상품 목록</h1>
	<table>
		<tr>
			<th>상품명</th>
			<th>가격</th>
			<th>재고</th>
			<th>이미지</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.productName}</td>
				<td>${product.price}</td>
				<td>${product.stock}</td>
				<td><img src="${product.imageUrl}" alt="상품 이미지"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
</body>
</html>