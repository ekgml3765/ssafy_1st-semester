<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- login 된 상태에서의 header, login 안된 상태의 header --%>
<c:set value="${pageContext.request.contextPath }" var="root" scope="session"></c:set>
<c:if test="${empty loginId }">
  <div>
    <a href="${root}/main?action=joinform">회원가입</a>
  </div>
</c:if>
<c:if test="${!empty loginId }">
  <div>${loginId}님  반갑습니다,
    <a href="${root}/main?action=logout">로그아웃</a>
  </div>
 <hr>
</c:if>
<hr>