<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
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
    <%@ include file="/include/header.jsp"%>
      <c:if test="${msg != null }">
        <h4>${msg}</h4>
      </c:if>
    <div id="loginDiv">
      <c:if test="${empty loginId}">
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
      <c:if test="${!empty loginId}">
        <div>${loginId}님 로그인함.</div>
      </c:if>
    </div>
    <br>
    <%@ include file="/include/footer.jsp"%>
  </div>
</body>

</html>