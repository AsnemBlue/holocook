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
<style>
td, th{border-bottom: 1px solid #ffa66d; height:30px; overflow: hidden;
  	text-overflow: ellipsis;
  	white-space: nowrap;}
table tr td:nth-of-type(1) {width:10%;}
table tr td:nth-of-type(2) {
	width : 50%;
	text-align:left;
}
table tr td:nth-of-type(2) a{display: inline;}
table tr td:nth-of-type(3) {width:30%;}
table tr td:nth-of-type(4) {width:10%;}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<div id="content">
			<table><tr>
				<td>번호</td><td>제목</td><td>작성일</td><td>조회수</td>
			</tr></table>
			<table>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.nNo}</td>
						<td><a href="${conPath }/noticeDetail.do?nNo=${dto.nNo }&pageNum=${pageNum }">${dto.nTitle}</a></td>
						<td>${dto.nRdate }</td>
						<td>${dto.nHit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
				<a href="${conPath }/notice.do?pageNum=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq pageNum }">
				[ <b> ${i }</b> ]
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/notice.do?pageNum=${i }"> ${i }</a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt}">
				<a href="${conPath }/notice.do?pageNum=${endPage+1 }">다음</a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>