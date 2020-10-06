<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style type="text/css">
div {
	width: 50%;
	margin: 0px auto;
	text-align: center;
	margin: 0px auto;
}

table {
	text-align: right;
	margin: 0px auto;
}

table td {
	padding: 5px;
}

button {
	width: 100px;
	padding: 5px;
	color: white;
	background-color: #AEC071;
}

#loginDiv {
	border: 3px solid gray;
	padding: 20px;
	width: 450px;
}

a {
	margin: 20px;
}
</style>
</head>
<body>
	<div>
		<h1>메인페이지</h1>
		
		<div id="loginDiv">
		<c:if test="${loginUser ne null}">
		<div>
			<strong>${loginUser}</strong>님 환영합니다.
			<a href="${root}/main?action=logout">로그아웃</a>
		</div>
		</c:if>
		
		<c:if test="${loginUser eq null}">
			<h3>로그인하여 주세요</h3>
			<form method="post" action="${root}/main">
				<input type="hidden" name="action" value="login">
				<table>
					<tr>
						<td>ID</td>
						<td><input type="text" name="id" required>
					</tr>
					<tr>
						<td>PASSWORD</td>
						<td><input type="password" name="pass" required>
					</tr>
					<tr>
						<td></td>
						<td><hr style="border-style: dotted"></td>
					</tr>
					<tr style="text-align: left">
						<td></td>
						<td><button type="submit">LOGIN</button></td>
					</tr>
				</table>
			</form>
			</c:if>
		</div>
		<br> <a href="${root}/main?action=add">상품등록</a> <a
			href="${root}/main?action=list">상품목록</a> <a
			href="${root}/main?action=last">마지막 등록한 상품</a>
	</div>
</body>
</html>