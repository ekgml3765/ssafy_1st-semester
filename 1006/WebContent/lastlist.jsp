<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a {
	margin: 20px;
}
</style>

</head>
<body>
	<h1>마지막 등록한 상품</h1>
	<div style="margin: 20px;">
		<table>
			<tr>
				<td><strong>상품 번호</strong></td>
				<td> ${cookie.pnum.value }</td>
			</tr>
			<tr>
				<td><strong>상 품 명</strong></td>
				<td>${cookie.pname.value }</td>
			</tr>
			<tr>
				<td><strong>상  품 가 격</strong></td>
				<td>${cookie.price.value }</td>
			</tr>
			<tr>
				<td><strong>상 품 설 명</strong></td>
				<td>${cookie.discription.value }</td>
			</tr>
		</table>
	</div>
	<br> <a href="${root}/main?action=add">상품등록</a> <a
			href="${root}/main?action=list">상품목록</a> <a
			href="${root}/main?action=last">마지막 등록한 상품</a>
</body>
</html>