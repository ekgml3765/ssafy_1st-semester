<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
	border: 2px solid black;
	width: 600px;
	height: 300px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="text-align: center">
		<h1>상품 목록</h1>
	</div>
	<div style="text-align: center;" >
		<table style="width: 600px;">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가격</th>
				<th>상품설명</th>
			</tr>
			<c:if test="${!empty product }">
			<tr>
				<td>${product.id }</td>
				<td>${product.name }</td>
				<td>${product.price }</td>
				<td>${product.description }</td>
			</tr>
			</c:if>
		</table>
		<a href="#">새상품 등록</a>
	</div>
</body>
</html>