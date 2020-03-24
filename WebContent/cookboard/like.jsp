<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#noheart,#redheart {width:30px;}
</style>
</head>
<body>
	<c:if test="${result eq 'black'}">
		<img src="${conPath }/img/heart.png" id="noheart">
	</c:if>
	<c:if test="${result eq 'red'}">
		<img src="${conPath }/img/heartR.png" id="redheart">
	</c:if>
</body>
</html>