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
<link href="${conPath }/css/member/modify_view.css" rel="stylesheet">
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
	<form action="${conPath }/modify.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="originPhoto" value="${member.mPhoto }">
		<table>
			<tr><th>아이디</th>
				<td><input type="text" name="mId" value="${member.mId }" readonly="readonly"></td>
				<td>내 지금 이미지</td>
			</tr>
			<tr><th>비밀번호</th>
				<td><input type="password" name="mPw" value="${member.mPw }" required="required"></td>
				<td rowspan="6"><div id="div_img"><img src="${conPath }/member_img/${member.mPhoto}" ></div></td>
			</tr>
			<tr><th>이름</th>
				<td><input type="text" name="mName" value="${member.mName }" required="required"></td>
			</tr>
			<tr><th>닉네임</th>
				<td><input type="text" name="mNick" value="${member.mNick }" required="required"></td>
			</tr>
			<tr><th>전화번호</th>
				<td><input type="text" name="mTel" value="${member.mTel }"></td>
			</tr>
			<tr><th>메일</th>
				<td><input type="email" name="mEmail" value="${member.mEmail }"></td>
			</tr>
			<tr><th>대표 이미지</th>
				<td><input type="file" name="mPhoto"></td>
			</tr>
			<tr><td colspan="3">
				<input type="submit" value="수정완료" class="btn_modify">
				<button type="button" class="btn_normal" onclick="location.href='goMain.do'">홈으로</button>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>