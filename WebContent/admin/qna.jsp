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
	if(${not empty result}){
		alert('${result }');
	}
});
</script>
<style>
td, th{border-bottom: 1px solid #ffa66d; height:30px; overflow: hidden;
  	text-overflow: ellipsis;
  	white-space: nowrap;}
table tr td:nth-of-type(1) {width:60px;}
table tr td:nth-of-type(2) {
	width : 490px;
	text-align:left;
}
table tr td:nth-of-type(2) a{display: inline;}
table tr td:nth-of-type(3) {width:90px;}
table tr td:nth-of-type(4) {width:150px;}
table tr td:nth-of-type(5) {width:50px;}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<div id="content">
			<table><tr>
				<td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>hit</td>
			</tr></table>
			<table>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.qNo}</td>
						<td>
						<c:if test="${dto.mBlack != 1}">
						<a href="${conPath }/qnaDetail.do?qNo=${dto.qNo }&pageNum=${pageNum }">
						<c:forEach var="i" begin="1" end="${dto.qIndent }">
							<c:if test="${i < dto.qIndent}">
							<img src="${conPath }/img/indent.gif">
							</c:if>
							<c:if test="${i eq dto.qIndent}">
							┗
							</c:if>
						</c:forEach>${dto.qTitle}</a>
						</c:if>
						<c:if test="${dto.mBlack eq 1}">이용 정지된 회원의 글입니다</c:if>
						</td>
						<td>${dto.aName==null? dto.mName : dto.aName }</td>
						<td><fmt:formatDate value="${dto.qRdate }" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
						<td>${dto.qHit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<c:if test="${not empty member or not empty admin}">
				<button onclick="location.href='qWriteView.do'" class="btn_normal">글쓰기</button>
			</c:if>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
				<a href="${conPath }/qna.do?pageNum=${startPage-1 }" class="btn_page"><</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq pageNum }" >
				 <span class="btn_page_now"> ${i }</span> 
				</c:if>
				<c:if test="${i != pageNum }">
					 <a href="${conPath }/qna.do?pageNum=${i }" class="btn_page"> ${i }</a> 
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt}">
				<a href="${conPath }/qna.do?pageNum=${endPage+1 }" class="btn_page">></a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>