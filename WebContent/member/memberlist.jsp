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
<!-- <script>
$(document).ready(function(){
	$('.btn_black').click(function(){
    	if(togCnt % 2 == 0){
    		 $('.toggle_menu').fadeIn("slow");
    	}else{
    		 $('.toggle_menu').fadeOut("fast");
    	}
    	togCnt++;
	});
	//홀로쿡 오버 이벤트
    $('#cook_center').mouseover(function(){
    	$('#cook_center_list').stop().slideDown();
    });
    $('#cook_center').mouseout(function(){
    	$('#cook_center_list').stop().slideUp();
    });
    //고객센터 오버이벤트
    $('#customer_center').mouseover(function(){
    	$('#customer_center_list').stop().slideDown();
    });
    $('#customer_center').mouseout(function(){
    	$('#customer_center_list').stop().slideUp();
    });
    
});
</script> -->
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
				 <span class="btn_page"> ${i }</span> 
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