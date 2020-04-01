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
<link href="${conPath }/css/cookboard/list.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="${conPath}/js/main/main.js"></script>
</head>
<c:if test="${not empty cbWriteResult }"> 
	<script>
		alert('${cbWriteResult }');
	</script>
</c:if>
<c:if test="${not empty errorMsg }">
	<script>
		alert('${errorMsg }');
		history.back();
	</script>
</c:if>
<body>
	<jsp:include page="../main/header.jsp"/> 
	<div id="content_wrap">
		<div id="slideWrap">
			<div class="slider">
				<div><a href="${conPath }/hitRecipe.do?keyword=파스타" ><img src="${conPath }/img/파스타.png"></a></div>
				<div><img src="http://placehold.it/1000x500"></div>
			</div>
		</div>
		<div id="content">
			<c:forEach items="${list }" var="dto"> 
				<div class="content_items">
					<a href="${conPath }/hitRecipe.do?cbNo=${dto.cbNo}&pageNum=${pageNum }" >
						<div>
							<div class="img_box"><img src="${conPath }/cookboard_img/${dto.cbImage}" class="img_food"></div>
							<p class="cbTitle">${dto.cbTitle }</p>
							<p class="mNick">chef. ${dto.mNick }</p>
							<p class="like_wrap"><img src="${conPath }/img/heartR.png" class="heartR"> ${dto.cbLike }</p>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
				<a href="${conPath }/goMain.do?pageNum=${startPage-1 }" class="btn_page"><</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq pageNum }">
				 <span class="btn_page_now"> ${i }</span> 
				</c:if>
				<c:if test="${i != pageNum }">
					 <a href="${conPath }/goMain.do?pageNum=${i }" class="btn_page"> ${i }</a> 
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt}">
				<a href="${conPath }/goMain.do?pageNum=${endPage+1 }" class="btn_page">></a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>