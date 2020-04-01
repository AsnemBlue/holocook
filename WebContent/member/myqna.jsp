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
<link href="${conPath}/css/style.css" rel="stylesheet">
<link href="${conPath }/css/member/myqna.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<table>
			<tr>
				<th>제목</th><th>내용</th><th>작성일</th><th>삭제</th>
			</tr>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.qTitle }</td>
					<td><a href="${conPath }/qnaDetail.do?qNo=${dto.qNo }">${dto.qContent }</a></td>
					<td><fmt:formatDate value="${dto.qRdate }" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
					<td><a href="${conPath }/qDelete.do?qNo=${dto.qNo }">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
				<a href="${conPath }/myqna.do?pageNum=${startPage-1 }&mId=${member.mId}" class="btn_page"><</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq pageNum }">
				 <span class="btn_page"> ${i }</span>
				</c:if>
				<c:if test="${i != pageNum }">
					 <a href="${conPath }/myqna.do?pageNum=${i }&mId=${member.mId}" class="btn_page"> ${i }</a> 
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt}">
				<a href="${conPath }/myqna.do?pageNum=${endPage+1 }&mId=${member.mId}" class="btn_page">></a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>