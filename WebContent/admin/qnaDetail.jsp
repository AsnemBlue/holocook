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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form id="content_wrap">
		<input type="hidden" name="qNo" value="${dto.qNo }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<table>
			<tr>
				<td>제목</td><td colspan="4">${dto.qTitle }</td>
			</tr>
			<tr>
				<td>작성자</td><td colspan="4">${dto.aName==null? dto.mName : dto.aName }</td>
			</tr>
			<tr>
				<td>첨부파일</td><td colspan="4">
				<c:if test="${!dto.qFile }">
					<a href="${conPath }/qna_file/${dto.qFile}">${dto.qFile }</a>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>작성일</td><td >${dto.qRdate }</td>
				<td>조회수</td><td>${dto.qHit }</td>
			</tr>
		</table>
		<div id="content">
			<pre>${dto.qContent }</pre>
		</div>
		<div id="control">
			<a href="${conPath }/qna.do?pageNum=${param.pageNum }"
				class="btn_normal">목록</a>
			<c:if test="${(not empty admin) or (not empty member and member.mId eq dto.mId)}">
				<a
					href="${conPath }/qDelete.do?qNo=${dto.qNo}&pageNum=${param.pageNum}"
					class="btn_normal">삭제</a>
				<a
					href="${conPath }/qModifyView.do?qNo=${dto.qNo}&pageNum=${param.pageNum}"
					class="btn_normal">수정</a>
			</c:if>
			<c:if test="${not empty member or not empty admin }">
				<a
					href="${conPath }/qReplyView.do?qNo=${dto.qNo}&pageNum=${param.pageNum}"
					class="btn_normal">답변</a>
			</c:if>

		</div>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>