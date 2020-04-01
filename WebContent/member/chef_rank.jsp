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
	<link href="${conPath }/css/member/chef_rank.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<div id="rank_head">
			<img src="${conPath }/img/chefOrange.png"> 쉐프 랭킹
		</div>
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
					<a href="${conPath }/"><p>${dto.mNick }</p></a>
				</div>
				<div class="rank_right">
					<p>
						<img src="${conPath }/img/heartR.png">${dto.mLike }</p>
				</div>
				<div class="rank_space"></div>
			</div>
		</c:forEach>
	</div>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE}">
			<a href="${conPath }/chef_rank.do?pageNum=${startPage-1 }" class="btn_arrow">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b> ${i }</b> ]
				</c:if>
			<c:if test="${i != pageNum }">
					[ <a href="${conPath }/chef_rank.do?pageNum=${i }" class="btn_page"> ${i }</a> ]
				</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/chef_rank.do?pageNum=${endPage+1 }" class="btn_arrow">다음</a>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>