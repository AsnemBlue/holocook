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
	var keyword = $('#input_keyword').val();
	var pageNum=1;
	var pageCnt=0;
	$('.btn_option').bind('click',function(){
		$('.btn_option').css({'color': 'black', 
			'background-color': '#ffbf95',
		    'border': '2px solid #de8c3d'});
		$(this).css({'color': 'white', 
			'background-color': '#f37321',
		    'border': '2px solid #e05d09'});
		$('#num'+1)
		.css('background-color','#f37321')
		.css('color','white');
		keyword = $('#input_keyword').val();
		var strDo = $(this).val();
		var mId = $('#mId').val();
		$.ajax({
			url : '${conPath }/'+strDo,
			dataType : 'html',
			data : "mId="+mId+"&keyword="+keyword,
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
	//이전으로
	$('#btn_previous').bind('click',function(){
		var order = keep_order;
		var cbcNoOption = keep_cbcNoOption;
		keyword = $('#input_keyword').val();
		pageNum = parseInt($('#startPage').val())-1;
		$('#btn_next').show();
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "mId="+mId+"&keyword="+keyword+"&pageNum="+pageNum,
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
		$('#btn_previous').show();		
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "mId="+mId+"&keyword="+keyword+"&pageNum="+pageNum,
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
		$.ajax({
			url : '${conPath }/listRecipe.do',
			dataType : 'html',
			data : "mId="+mId+"&keyword="+keyword+"&pageNum="+pageNum,
			success : function(data,status){
				$('#call_list').html(data);
			}
		});
	});
	$('#btn_option0').trigger('click');
	
});
</script>
<style>
#option_class{
	width:1000px;
	height: 65px;
	margin:0 auto;
}
.btn_option{
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

.btn_order{
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
.btn_option{
	float: left;
}
.btn_order{
	float: right;
    margin-top: 33px;
}

</style>
</head>

<body>
	<input type="hidden" name="mId" value="${param.mId }" id="mId">
	<jsp:include page="../main/header.jsp"/>
	<div id="option_class">
	<button class="btn_option" id="btn_option0" value="myLike_List.do">좋아요</button>
	<button class="btn_option" id="btn_option1" value="my_List.do?my_mId=${member.mId }">내레시피</button>
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