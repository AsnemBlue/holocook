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
<link href="${conPath }/css/member/memberlist.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="rank_head">
			<img src="${conPath }/img/chefOrange.png"> 회원목록
	</div>
	<form action="${conPath }/memberlistView.do">
		<input type="text" name="search_mId" value="${param.search_mId}">
		<input type="submit" value="검색" class="btn_normal">
	</form>
		<c:forEach items="${list }" var="dto" varStatus="status">
			<div class="rank_list">
				<div class="rank_space"></div>
				<div class="rank_rankNum">
					<c:if test="${status.count<10}">
					${pageNum == 1?"":(pageNum-1)}${status.count}.
					</c:if>
					<c:if test="${status.count==10}">
					${pageNum}0.
					</c:if>
				</div>
				<div class="rank_left">
					<img src="${conPath }/member_img/${dto.mPhoto}">
				</div>
				<div class="rank_center">
					<a href="${conPath }/myInfo.do?mId=${dto.mId }"><p>${dto.mNick }(${dto.mId }) </p></a>
					<a href="${conPath }/myrecipe.do?mId=${dto.mId }" class="btn_admin">레시피</a>
					<a href="${conPath }/myqna.do?mId=${dto.mId }" class="btn_admin">Qna</a>
				</div>
				<div class="rank_right">
					<c:if test="${dto.mBlack eq 1}">
						<p><a href="${conPath }/memberBlack.do?mId=${dto.mId}" class="btn_admin" style="background-color:black;">Black</a></p>
					</c:if>
					<c:if test="${dto.mBlack eq 0}">
						<p><a href="${conPath }/memberBlack.do?mId=${dto.mId}" class="btn_admin">Black</a></p>
					</c:if>
				</div>
				<div class="rank_space"></div>
			</div>
		</c:forEach>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE}">
			<a href="${conPath }/memberlistView.do?pageNum=${startPage-1 }&search_mId=${param.search_mId}" class="btn_arrow"><</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				 <span class="btn_page_now"> ${i }</span> 
				</c:if>
			<c:if test="${i != pageNum }">
					<a href="${conPath }/memberlistView.do?pageNum=${i }&search_mId=${param.search_mId}" class="btn_page"> ${i }</a>
				</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/memberlistView.do?pageNum=${endPage+1 }&search_mId=${param.search_mId}" class="btn_arrow">></a>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>