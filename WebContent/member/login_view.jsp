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
<link href="${conPath }/css/member/login_view.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${conPath}/js/member/login_view.js"></script>
</head>
<body>
	<c:if test="${not empty msg_join }">
		<script>
			alert('${msg_join}');
		</script>
	</c:if>
	<header>
		<div class="office_logo">
			<a href="${conPath }/goMain.do">
				<img src="${conPath }/img/HoloCookOrange.png">
			</a>
		</div>
	</header>
	<div id="container">
		<form action="${conPath }/login.do" onsubmit="return loginChk(this);" method="post"  id="form_login">
			<input type="text"     id="mId" name="mId" class="input_login" placeholder="아이디" value="${mId }">
			<div class="error" id="err_empty_mId" style="display:none">아이디를 입력해주세요.</div>
			<input type="password" id="mPw" name="mPw" class="input_login" placeholder="비밀번호"  >
			<div class="error" id="err_empty_mPw" style="display:none">비밀번호를 입력해주세요.</div>
			<div class="error" id="err_login" style="display:none">가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.</div>
			
			<input type="submit" id="btn_login" title="로그인" alt="로그인" value="로그인" class="btn_normal">
			<a href="${conPath }/joinView.do" class="btn_normal">회원가입</a>
			<a href="${conPath }/goMain.do" class="btn_normal">홈으로</a>
		
		</form>
		
	</div>	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>