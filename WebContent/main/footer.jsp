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
<link href="${conPath }/css/main/footer.css" rel="stylesheet">
</head>
<body>
	<footer class='whole_wrap'>
	<hr>
		<div class="office_logo">
			<a href="${conPath }/goMain.do">
				<img src="${conPath }/img/HoloCookOrange.png">
			</a>
		</div>
		<div class="office_address">
			<ul>
				<li>서울시 종로구 수표로 105 육의전빌딩 8F | TEL: 02-000-0000 | FAX :
					02-777-7777</li>
				<li>사업자등록번호 : 000-12-00000 | 통신판매업신로 : 종로 제0000호 | 대표: 박기범</li>
				<li>홀로쿡 문의 메일: gbpark0524@gmail.com</li>
			</ul>
		</div>
		<div class="copyright">ⓒ2020 HOLOCOOK.Co.,Ltd. All Right
			Reserved.</div>
	</footer>
</body>
</html>