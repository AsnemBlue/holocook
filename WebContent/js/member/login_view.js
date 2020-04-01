$(document).ready(function(){
	function loginChk(form){
		$('#err_empty_mId').css('display','none');
		$('#err_empty_mPw').css('display','none');
		$('#err_login').css('display','none');
		if($('#mId').val()==""){
			$('#err_empty_mId').css('display','block');
			$('#mId').focus();
			return false;
		}
		if($('#mPw').val()==""){
			$('#err_empty_mPw').css('display','block');
			$('#mPw').focus();
			return false;
		}
	};
});