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
<link href="${conPath }/css/style.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	if(${not empty error}){
		alert('${error}');
	}
	if(${not empty result}){
		alert('${result}');
	}
});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form id="content_wrap">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="nNo" value="${dto.nNo }">
		<table>
			<tr>
				<td>제목</td><td colspan="4">${dto.nTitle }</td>
			</tr>
			<tr>
				<td>작성자</td><td colspan="4">${dto.aName }</td>
			</tr>
			<tr>
				<td>작성일</td><td >${dto.nRdate }</td>
				<td>조회수</td><td>${dto.nHit }</td>
			</tr>
		</table>
		<div id="content">
			<pre>${dto.nContent }</pre>
		</div>
		<div id="control">
			<ul>
				<li><a href="${conPath }/notice.do?">목록</a></li>
				<!--admin 전용 단추-->
				<c:if test="${empty member and not empty admin}">
					<li><a href="${conPath }/nModifyView.do?aId=${admin.aId}&nNo=${dto.nNo }">수정</a></li>
					<li><a href="${conPath }/nDelete.do?nNo=${dto.nNo }">삭제</a></li>
				</c:if>
			</ul>
		</div>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>