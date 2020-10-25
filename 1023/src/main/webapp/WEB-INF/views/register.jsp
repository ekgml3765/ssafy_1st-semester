<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.d1 {
	background-color: orange;
}

body {
	border: 2px solid black;
	width: 600px;
	height: 300px;
}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="text-align: center">
		<h1>상품 등록</h1>
	</div>
	<div>

		<form method="post" action="${root}/register">
			<table>
				<tr>
					<td class="d1">아이디</td>
					<td><input type="text" name="id"></td>
					<td class="d1">상품명</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td class="d1">가격</td>
					<td><input type="text" name="price"></td>
					<td class="d1">설명</td>
					<td><input type="text" name="description"></td>
					<td><input type="submit" value="제출"/></td>
				</tr>
			</table>
		</form>
		
		<a href="${root }/WEB_INF/views/list.jsp">목록보기</a>
	</div>
</body>
</html>