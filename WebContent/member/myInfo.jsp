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
<link href="${conPath }/css/member/join_view.css" rel="stylesheet">
<link href="${conPath }/css/member/myInfo.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<header>
		<div class="office_logo">
			<a href="${conPath }/goMain.do">
				<img src="${conPath }/img/HoloCookOrange.png">
			</a>
		</div>
	</header>
	<div id="content_wrap">
	<form action="${conPath }/modifyView.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="originPhoto" value="${dto.mPhoto }">
		<table>
			<tr ><th>아이디</th>
				<td>${dto.mId }</td>
				<td>내 대표 이미지</td>
			</tr>
			<tr ><th>이름</th>
				<td>${dto.mName }</td>
				<td rowspan="5"><div id="div_img"><img src="${conPath }/member_img/${dto.mPhoto}" ></div></td>
			</tr>
			<tr ><th>닉네임</th>
				<td>${dto.mNick }</td>
			</tr>
			<tr><th>전화번호</th>
				<td>${dto.mTel }</td>
			</tr>
			<tr><th>메일</th>
				<td>${dto.mEmail }</td>
			</tr>
			<tr><td><img src="${conPath }/img/heartR.png" id="heartR"></td>
			<td id="heart_num">${dto.mLike }</td>
			</tr>
			<tr><td colspan="3">
				<c:if test="${not empty member and empty admin}">
				<input type="submit" value="수정하기" class="btn_normal">
				</c:if>
				<button type="button" class="btn_normal" onclick="location.href='goMain.do'">홈으로</button>
				<button type="button" class="btn_normal" onclick="history.back()">뒤로</button>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>