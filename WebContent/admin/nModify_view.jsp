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
<link href="conPath/css/style.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<body>
	<jsp:include page="../main/header.jsp"/>
	<form action="${conPath }/nModify.do">
		<input type="hidden" name="aId" value="${admin.aId }">
		<input type="hidden" name="nNo" value="${dto.nNo }">
		<table>
			<tr>
				<td><label for="nTitle">제목</label></td>
				<td><input type="text" name="nTitle" id="nTitle" value="${dto.nTitle }"></td>
			</tr>
			<tr><td colspan="2">
				<textarea rows="5" cols="20" name="nContent">${dto.nContent }</textarea>
			</td></tr>
			<tr><td colspan="2">
				<input type="submit" value="확인">
			</td></tr>
		</table>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>