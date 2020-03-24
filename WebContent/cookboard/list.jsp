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
<link href="${conPath }/css/cookboard/list.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
	#img_writer{
		height:50px;
	}
	.mNick{color: #a0a0a0;}
</style>
</head>
<body>
	<div id="content_wrap">
	<input type="hidden" name="pageNum" value="${pageNum }" id="pageNum">
	<input type="hidden" name="startPage" value="${startPage }" id="startPage">
	<input type="hidden" name="endPage" value="${endPage }" id="endPage">
	<input type="hidden" name="pageCnt" value="${pageCnt }" id="pageCnt">
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
		
	</div>
</body>
</html>