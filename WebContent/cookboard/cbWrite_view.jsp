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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
	.item_1{dispalay:none;}
</style>
<script>
$(document).ready(function(){
	var cbdCount = 1;
	
	$('.items').hide();
	$('#item_1').show();
    $('#item_plus').click(function(){
		$('#item_minus').show();
		$('#item_'+(cbdCount+1)).show();
		if(cbdCount ==14){
			 $('#item_plus').hide();
		}
		cbdCount++;
		$('#cbdCount').val(cbdCount);
    });
    $('#item_minus').click(function(){
		$('#item_plus').show();
		$('#item_'+(cbdCount)).hide();
		if(cbdCount ==2){
		 	$('#item_minus').hide();
		}
		cbdCount--;
		$('#cbdCount').val(cbdCount);
    });
    
    
});
	
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<form action="${conPath }/cbWrite.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="mId" value="${member.mId }">
			<input type="hidden" name="cbdCount" id="cbdCount" value="1">
			<table>
				<caption>레시피 등록</caption>
				<tr>
					<th>레시피 제목</th><th><input type="text" name="cbTitle" required="required"></th>
				</tr>
				<tr>
					<th>대표 이미지</th><th><input type="file" name="cbImage" ></th>
				</tr>
				<tr>
					<th>재료</th><th><textarea name="cbIngredient" rows="5" cols="20"></textarea></th>
				</tr>
				<tr>
					<th>카테고리</th><th>
					<select name='cbcNo'>
  					<option value='1' selected="selected">알뜰쿠킹</option>
  					<option value='2'>욜로쿠킹</option>
					</select></th>
				</tr>
				<tr class="items" id="item_1"><td colspan="2">
						<div >
						<span class="btn_arrow">1</span>
						<textarea name="cbdContent1" rows="5" cols="20"></textarea>
						<input type="file" name="cbdImage1">
						</div>
					</td></tr>
				<c:forEach var="idx" begin="2" end="15" varStatus="status">
					<tr class="items" id="item_${status.current}"><td colspan="2">
						<div >
						<span class="btn_arrow">${status.current}</span>
						<textarea name="cbdContent${status.current}" rows="5" cols="20"></textarea>
						<input type="file" name="cbdImage${status.current}">
						</div>
					</td></tr>
				</c:forEach>
				
				<tr><td colspan="2">
					<button type="button" id="item_plus" class="btn_arrow">(+)</button>
					<button type="button" id="item_minus" class="btn_arrow">(-)</button>
				</td></tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록" class="btn_normal">
						<input type="reset" value="취소" class="btn_normal">
						<input type="button" value="뒤로" onclick="history.back()" class="btn_normal">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>