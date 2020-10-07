<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
    
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
 <%@ include file="/include/header.jsp" %>
 <table border="1">
	<tr>
		<th>상품번호</th>
		<th>상품명</th>
		<th>상품가격</th>
		<th>상품설명</th>
	</tr>
	<c:if test="${list.size() != 0}">
		<c:forEach var="pro" items="${list}">
			<tr>
			<td>${pro.pnum}</td>
			<td>${pro.pname}</td>
			<td>${pro.price}</td>
			<td>${pro.dc}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<form method="get" action="${root}/Main">
		<input type="hidden" name="action" value="search">
		<select name="option">
			<option value="name">이름으로 검색</option>
			<option value="price">가격으로 검색</option>
		</select>
		
		<input type="text" name="input">
		<input type="submit" value="검색">
</form>
  <%@ include file="/include/footer.jsp" %>
</body>
</html>