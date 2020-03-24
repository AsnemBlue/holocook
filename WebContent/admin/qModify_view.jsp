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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form action="${conPath }/qModify.do?qNo=${dto.qNo }&pageNum=${param.pageNum}" method="post" enctype="multipart/form-data">
	<input type="hidden" name="qNo" value="${dto.qNo }">
	<input type="hidden" name="qOriginFile" value="${dto.qFile }">
		<table>
			<tr>
				<td><label for="qTitle">제목</label></td><td><input type="text" name="qTitle" required="required" value=${dto.qTitle }></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" name="qFile">현재파일 : ${dto.qFile }</td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="qContent" rows="5" cols="20">${dto.qContent }</textarea></td>
			</tr>			
			<tr>
				<td colspan="2"><input type="submit" value="수정 완료"></td>
			</tr>			
		</table>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>