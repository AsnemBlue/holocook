$(document).ready(function(){
	var chk = /^[a-zA-Z0-9]{4,20}$/;
	var nickChk = /^[가-힣a-zA-Z0-9]{2,20}$/;
	$('#mId').keyup(function(){
		if (!chk.test($('#mId').val())) {
			$('#err_mId').html('4~20자의 영문, 숫자만 사용 가능합니다.');
			return false;
		}else{
			var mId=$('input[name="mId"]').val();
			$.ajax({
				url : 'idChk.do',
				type : 'get',
				data : 'mId='+mId,
				success : function(data,status){
					$('#err_mId').html(data);
				}
			})
		}
	});
	$('#mNick').keyup(function(){
		if (!nickChk.test($('#mNick').val())) {
			$('#err_mNick').html('2~20자의 영문, 숫자, 한글만 사용 가능합니다.');
			return false;
		}else{
			$.ajax({
				url : 'nickChk.do',
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
	$('#form_join').submit(function(){
		if($('#err_mId').html().trim() != "사용가능한 ID입니다."){
			return false;
		}else if($('#err_mNick').html().trim() != "사용가능한 닉네임입니다."){
			return false;
		}else if($('#err_mPw').html()!=""){
			return false;
		}else if($('#err_mPwChk').html()!=""){
			return false;
		}else{
			return true;
		}
	});
});