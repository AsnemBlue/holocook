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
#reply_wrap{
	position: relative;
    width: 900px;
}
#rank_head {
	font-size: 40px;
    color: #f37321;
}
#rank_head img{width:100px;}
.rank_rankNum{
	font-size:40px;
	width:60px;
}
.rank_list{
	align-items: center;
	display: flex;
    margin: 10px 0;
    padding: 10px 0;
    border-top: 1px solid #ffd1b3;
}
.rank_left{
	height: 70px;
    width: 70px;
    overflow: hidden;
    border-radius: 70px;
}
.rank_left img{
	display: inline-block;
	width: 70px;
}
.rank_center{
	width: 200px;
}
.rank_center:hover a{
	cursor: pointer;
	font-weight:500;
	color:#f37321;
}
.rank_right{
	width:60px;
}
.rank_right img{
	width: 24px;
    vertical-align: sub;
}
.rank_space{
	width:200px;
}
</style>
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
					<a href="${conPath }/"><p>${dto.mNick } </p></a>
				</div>
				<div class="rank_right">
					<p><img src="${conPath }/img/heartR.png">${dto.mLike }</p>
				</div>
				<div class="rank_space"></div>
			</div>
		</c:forEach>
	</div>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE}">
			<a href="${conPath }/chef_rank.do?pageNum=${startPage-1 }">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b> ${i }</b> ]
				</c:if>
			<c:if test="${i != pageNum }">
					[ <a href="${conPath }/chef_rank.do?pageNum=${i }"> ${i }</a> ]
				</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/chef_rank.do?pageNum=${endPage+1 }">다음</a>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>