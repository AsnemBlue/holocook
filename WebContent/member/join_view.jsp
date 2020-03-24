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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	var chk = /^[a-zA-Z0-9]{4,20}$/;
	var nickChk = /^[가-힣a-zA-Z0-9]{4,20}$/;
	$('#mId').keyup(function(){
		if (!chk.test($('#mId').val())) {
			$('#err_mId').html('4~20자의 영문, 숫자만 사용 가능합니다.');
			return false;
		}else{
			$.ajax({
				url : '${conPath }/idChk.do',
				type : 'get',
				dataType : 'html',
				data : 'mId='+$('input[name="mId"]').val(),
				success : function(data,status){
					$('#err_mId').html(data);
				}
			})
		}
	});
	$('#mNick').keyup(function(){
		if (!nickChk.test($('#mNick').val())) {
			$('#err_mNick').html('4~20자의 영문, 숫자, 한글만 사용 가능합니다.');
			return false;
		}else{
			$.ajax({
				url : '${conPath }/nickChk.do',
				type : 'get',
				dataType : 'html',
				data : 'mNick='+$('input[name="mNick"]').val(),
				success : function(data,status){
					$('#err_mNick').html(data);
				}
			})
		}
	});
	$('#mPw').keyup(function(){
		if (!chk.test($('#mPw').val())) {
			$('#err_mPw').html('4~20자의 영문, 숫자만 사용 가능합니다.');
			return false;
		}else{
			$('#err_mPw').html('');
		}
		if($('#mPw').val()!=$('#mPwChk').val()){
			$('#err_mPwChk').html("비밀번호가 불일치 합니다");
		}else{
			$('#err_mPwChk').html("");
		}
	});
	$('#mPwChk').keyup(function(){
		if($('#mPw').val()!=$('#mPwChk').val()){
			$('#err_mPwChk').html("비밀번호가 불일치 합니다");
		}else{
			$('#err_mPwChk').html("");
		}
	});
	$('form').submit(function(){
	});
});
</script>
</head>
<body>
	<header>
		<div class="office_logo">
			<a href="${conPath }/goMain.do">
				<img src="${conPath }/img/HoloCookOrange.png">
			</a>
		</div>
	</header>
	<div id="container">
		<form action="${conPath }/join.do" method="post" id="form_join" >
			<h3>
			<label for="mId">아이디</label>
			</h3>
			<input type="text"     id="mId" name="mId" class="input_join" >
			<div class="error" id="err_mId"></div>
			<h3><label for="mId">비밀번호</label></h3>
			<input type="password" id="mPw" name="mPw" class="input_join" >
			<div class="error" id="err_mPw" >비밀번호를 입력해주세요.</div>
			<h3><label for="mId">비밀번호 재확인</label></h3>
			<input type="password" id="mPwChk" name="mPwChk" class="input_join" >
			<div class="error" id="err_mPwChk"></div>
			<h3><label for="mId">이름</label></h3>
			<input type="text" id="mName" name="mName" class="input_join" >
			<div class="error" id="err_mName" style="display:none">이름을 입력해주세요.</div>
			<h3><label for="mId">닉네임</label></h3>
			<input type="text" id="mNick" name="mNick" class="input_join" >
			<div class="error" id="err_mNick" >별명를 입력해주세요.</div>
			<h3><label for="mId">전화번호<span class="choice">(선택)</span></label></h3>
			<input type="text" id="mTel" name="mTel" class="input_join" >
			<h3><label for="mId">메일<span class="choice">(선택)</span></label></h3>
			<input type="text" id="mEmail" name="mEmail" class="input_join" >
			<input type="submit" id="btn_join" title="가입하기" alt="가입하기" value="가입하기" class="btn_normal">
			<a href="${conPath }/goMain.do" class="btn_normal">홈으로</a>
		</form>
		
	</div>	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>