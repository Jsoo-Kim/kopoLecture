<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring_practice.productregistration.model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
    <h1>상품 목록</h1>
    <table border="1">
        <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>재고</th>
            <th>이미지</th>
        </tr>
        <% 
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) { 
                for (Product product : products) { 
        %>
                <tr>
                    <td><%= product.getProductName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getStock() %></td>
                    <td><img src="<%= product.getImageUrl() %>" alt="상품 이미지" width="100" height="100"></td>
                </tr>
        <% 
                } 
            } else { 
        %>
            <tr>
                <td colspan="4">등록된 상품이 없습니다.</td>
            </tr>
        <% 
            } 
        %>
    </table>
    
        <form action="registerProductForm" method="get">
        <input type="submit" value="상품 등록 페이지로">
    </form>
    
</body>
</html>
