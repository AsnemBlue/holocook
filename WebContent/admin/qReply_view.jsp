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
	<div id="content_wrap">
	<form action="${conPath }/qReply.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="aId" value="${admin.aId }">
	<input type="hidden" name="mId" value="${member.mId }">
	<input type="hidden" name="qNo" value="${param.qNo }">
		<table>
			<tr>
				<td><label for="qTitle">제목</label></td><td><input type="text" name="qTitle" required="required"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" name="qFile"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="qContent" rows="5" cols="20"></textarea></td>
			</tr>			
			<tr>
				<td colspan="2">
					<input type="submit" value="작성 완료" class="btn_normal">
					<a href="javascript:locateion:history.back()" class="btn_normal">뒤로가기</a>
					<a href="${conPath }/goMain.do" class="btn_normal">홈으로</a>
				</td>
			</tr>			
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>