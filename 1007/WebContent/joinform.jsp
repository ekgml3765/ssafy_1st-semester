<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%@ include file="/include/header.jsp" %>
  <h1>회원가입</h1>
  <form method="post" action="${root}/main?action=join">
        <input type="text" name="id" placeholder="id"><br/>
        <input type="text" name="name" placeholder="name"><br/>
        <input type="password" name="password" placeholder="pw"><br/>
        <input type="submit">
    </form>
    <%@ include file="/include/footer.jsp"%>
</body>
</html>