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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	var keep_cbcNoOption="";
	var keep_order="cbNo";
	var pageNum=1;
	var pageCnt=0;
	$('#btn_previous').hide();
	$('#btn_next').show();
	$('#num1').css({'color': 'white', 
		'background-color': '#f37321',});
	//게시판 class별 조회 선택 버튼
	//빈칸:전체, 1:알뜰쿠킹, 2:욜로쿠킹
	$('.btn_option').bind('click',function(){
		$('.btn_option').css({'color': 'black', 
			'background-color': '#ffbf95',
		    'border': '2px solid #de8c3d'});
		$(this).css({'color': 'white', 
			'background-color': '#f37321',
		    'border': '2px solid #e05d09'});
		$('.btn_page').css({'color': '#f37321', 
			'background-color': 'white',});
		$('#num1').css({'color': 'white', 
			'background-color': '#f37321',});
		var cbcNoOption = $(this).val();
		keep_cbcNoOption = cbcNoOption;
		var order = keep_order;
		keyword = $('#input_keyword').val();
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "orderOption="+order+"&cbcNoOption="+cbcNoOption+"&keyword="+keyword,
			success : function(data,status){
				$('#call_list').html(data);
				pageCnt = parseInt($('#pageCnt').val());
				if(parseInt(pageNum/10) == parseInt(pageCnt/10)){
					$('#btn_next').hide();
				}
				$('.btn_page' ).each( function() {
					$(this).show();
					if(parseInt($(this).attr('value')) > pageCnt){
						$(this).hide();
					}
			    });
			}
		});
	});
	//정렬순 조회 선택 버튼
	$('.btn_order').bind('click',function(){
		$('.btn_order').css({'color': 'black', 
			'background-color': '#ffbf95',
		    'border': '2px solid #de8c3d'});
		$(this).css({'color': 'white', 
			'background-color': '#f37321',
		    'border': '2px solid #e05d09'});
		$('.btn_page').css({'color': '#f37321', 
			'background-color': 'white',});
		$('#num1').css({'color': 'white', 
			'background-color': '#f37321',});
		var order = $(this).val();
		keep_order = order;
		var cbcNoOption = keep_cbcNoOption;
		keyword = $('#input_keyword').val();
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "orderOption="+order+"&cbcNoOption="+cbcNoOption+"&keyword="+keyword,
			success : function(data,status){
				$('#call_list').html(data);
				pageCnt=parseInt($('#pageCnt').val());
				if(parseInt(pageNum/10) == parseInt(pageCnt/10)){
					$('#btn_next').hide();
				}
				$('.btn_page' ).each( function() {
					$(this).show();
					if(parseInt($(this).attr('value')) > pageCnt){
						$(this).hide();
					}
			    });
			}
		});
	});
	//이전으로
	$('#btn_previous').bind('click',function(){
		var order = keep_order;
		var cbcNoOption = keep_cbcNoOption;
		keyword = $('#input_keyword').val();
		pageNum = parseInt($('#startPage').val())-1;
		$('.btn_page').css({'color': '#f37321', 
			'background-color': 'white',});
		$('#num10').css({'color': 'white', 
			'background-color': '#f37321',});
		$('#btn_next').show();
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "orderOption="+order+"&cbcNoOption="+cbcNoOption+"&pageNum="+pageNum+"&keyword="+keyword,
			success : function(data,status){
				$('#call_list').html(data);
				if(pageNum<11){
					$('#btn_previous').hide();
				}
				$( '.btn_page' ).each( function() {
					var temp = parseInt($(this).attr('value'));
					temp = temp - 10;
					$(this).attr('value',temp);
					$(this).text(temp);
					$(this).show();
			    });
			}
		});
	});
	//다음으로
	$('#btn_next').bind('click',function(){
		var order = keep_order;
		var cbcNoOption = keep_cbcNoOption;
		keyword = $('#input_keyword').val();
		pageNum = parseInt($('#endPage').val())+1;
		$('.btn_page').css({'color': '#f37321', 
			'background-color': 'white',});
		$('#num1').css({'color': 'white', 
			'background-color': '#f37321',});
		$('#btn_previous').show();		
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "orderOption="+order+"&cbcNoOption="+cbcNoOption+"&pageNum="+pageNum+"&keyword="+keyword,
			success : function(data,status){
				$('#call_list').html(data);
				pageCnt = parseInt($('#pageCnt').val());
				if(parseInt(pageNum/10) == parseInt(pageCnt/10)){
					$('#btn_next').hide();
				}
				$( '.btn_page' ).each( function() {
					var temp = parseInt($(this).attr('value'));
					temp = temp + 10;
					$(this).attr('value',temp);
					$(this).text(temp);
					if(parseInt($(this).attr('value')) > pageCnt){
						$(this).hide();
					}
			    });
			}
		});
	});
	//page 숫자 눌렀을 경우 페이지 이동.
	$('.btn_page').bind('click',function(){
		var order = keep_order;
		var cbcNoOption = keep_cbcNoOption;
		keyword = $('#input_keyword').val();
		pageNum = $(this).attr('value');
		$('.btn_page').css({'color': '#f37321', 
			'background-color': 'white',});
		$('#num'+pageNum).css({'color': 'white', 
			'background-color': '#f37321',});
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "orderOption="+order+"&cbcNoOption="+cbcNoOption+"&pageNum="+pageNum+"&keyword="+keyword,
			success : function(data,status){
				$('#call_list').html(data);
			}
		});
	});

	var keyword = $('#input_keyword').val();
 	if($('#option').val() == 'search_direct'){
 		$('#btn_option0').trigger('click');
		$('#btn_cbNo').trigger('click'); 
	}
 	if($('#cbcOption').val()==1){
 		$('#btn_option1').trigger('click');
 		$('#btn_cbNo').trigger('click');
 	}
 	if($('#cbcOption').val()==2){
 		$('#btn_option2').trigger('click');
 		$('#btn_cbNo').trigger('click');
 	}
});
</script>
<style>
#option_class {
	width: 1000px;
	height: 65px;
	margin: 0 auto;
}

.btn_option {
	width: 120px;
	box-sizing: border-box;
	cursor: pointer;
	background-color: #ffbf95;
	border: 2px solid #de8c3d;
	padding: 10px 30px;
	font-size: 14px;
	border-radius: 10px 10px 3px 3px;
	margin-top: 20px;
}

.btn_order {
	box-sizing: border-box;
	cursor: pointer;
	background-color: #ffbf95;
	border: 2px solid #de8c3d;
	padding: 5px 10px;
	font-size: 12px;
	border-radius: 3px;
	width: 80px;
	margin-: 0 2px;
	margin: 0 1px;
}

.btn_option {
	float: left;
}

.btn_order {
	float: right;
	margin-top: 33px;
}


</style>
</head>

<body>
	<!-- 처음 페이지 초기화시 스크립트로 전달할 ajax전송용 파라미터 -->
	<input type="hidden" name="option" value="${param.option }" id="option">
	<input type="hidden" name="cbcOption" value="${param.cbcOption }" id="cbcOption">
	<jsp:include page="../main/header.jsp"/>
	<div id="option_class">
	<button class="btn_option" id="btn_option0" value="">전체</button>
	<button class="btn_option" id="btn_option1" value="1">알뜰쿠킹</button>
	<button class="btn_option" id="btn_option2" value="2">욜로쿠킹</button>
	<button class="btn_order" id="btn_cbNo" value="cbNo">최신순</button>
	<button class="btn_order" id="btn_cbLike" value="cbLike">추천순</button>
	</div>
	<div id="call_list"> 
	</div>
	<div class="paging">
		<button id="btn_previous" class="btn_arrow" ><</button>
		<c:forEach var="i" begin="1" end="10">
			<button id="num${i }" class="btn_page" value="${i }">${i }</button>
		</c:forEach>
		<button id="btn_next" class="btn_arrow" >></button>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>