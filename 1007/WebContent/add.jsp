<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%@ include file="/include/header.jsp"%>
  <h1>상품등록</h1>
  <form method="post" action="${root}/main?action=add">
    <input type="text" name="pnum" placeholder="상품 번호">
    <input type="text" name="pname" placeholder="상품명">
    <input type="text" name="price" placeholder="상품가격">
    <input type="text" name="dc" placeholder="상품 설명">
    <input type="submit">
  </form>
  <%@ include file="/include/footer.jsp"%>
</body>
</html>