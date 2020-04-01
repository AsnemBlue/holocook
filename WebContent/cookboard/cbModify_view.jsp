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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
$(document).ready(function(){
    
	var cbdCount = $('#cbdCount').val();
	$('.items').hide();
	for(var i=1; i<=cbdCount; i++){
		$('#item_'+i).show();
	}
	
    $('#item_plus').click(function(){
		$('#item_minus').show();
		$('#item_'+(Number(cbdCount)+1)).show();
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
		<form action="${conPath }/cbModify.do?cbNo=${param.cbNo }" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cbNo" value="${param.cbNo }">
			<input type="hidden" name="mId" value="${member.mId }">
			<input type="hidden" name="origin_cbImage" value="${top_content.cbImage }">
			<input type="text" name="cbdCount" id="cbdCount" value="${param.cbdCount}">
			<table>
				<caption>레시피 수정</caption>
				<tr>
					<th>레시피 제목</th><th><input type="text" name="cbTitle" required="required" value="${top_content.cbTitle }"></th>
				</tr>
				<tr>
					<th><img src="${conPath }/cookboard_img/${top_content.cbImage }" class="img_food"></th>
				</tr>
				<tr>
					<th>대표 이미지</th><th><input type="file" name="cbImage" >${top_content.cbImage }</th>
				</tr>
				<tr>
					<th>재료</th><th><textarea name="cbIngredient" rows="5" cols="20" >${top_content.cbIngredient }</textarea></th>
				</tr>
				<tr>
					<th>카테고리</th><th>
					<select name='cbcNo'>
  					<option value='1' selected='${top_content.cbcNo==1?"selected":"" }'>알뜰쿠킹</option>
  					<option value='2' selected='${top_content.cbcNo==2?"selected":"" }'>욜로쿠킹</option>
					</select></th>
				</tr>
				<tr class="items" id="item_1">
					<td colspan="2">
						<div>
							1
							<textarea name="cbdoContent1" rows="5" cols="20">${bottom_content1.cbdContent }</textarea>
							<input type="file" name="cbdoImage1">
							<img src="${conPath }/cookboard_img/${bottom_content1.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_2">
					<td colspan="2">
						<div>
							2
							<textarea name="cbdoContent2" rows="5" cols="20">${bottom_content2.cbdContent }</textarea>
							<input type="file" name="cbdoImage2">
							<img src="${conPath }/cookboard_img/${bottom_content2.cbdImage }" class="img_food">

						</div>
					</td>
				</tr>
				<tr class="items" id="item_3">
					<td colspan="2">
						<div>
							3
							<textarea name="cbdoContent3" rows="5" cols="20">${bottom_content3.cbdContent }</textarea>
							<input type="file" name="cbdoImage3">
							<img src="${conPath }/cookboard_img/${bottom_content3.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_4">
					<td colspan="2">
						<div>
							4
							<textarea name="cbdoContent4" rows="5" cols="20">${bottom_content4.cbdContent }</textarea>
							<input type="file" name="cbdoImage4">
							<img src="${conPath }/cookboard_img/${bottom_content4.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_5">
					<td colspan="2">
						<div>
							5
							<textarea name="cbdoContent5" rows="5" cols="20">${bottom_content5.cbdContent }</textarea>
							<input type="file" name="cbdoImage5">
							<img src="${conPath }/cookboard_img/${bottom_content5.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_6">
					<td colspan="2">
						<div>
							6
							<textarea name="cbdoContent6" rows="5" cols="20">${bottom_content6.cbdContent }</textarea>
							<input type="file" name="cbdoImage6">
							<img src="${conPath }/cookboard_img/${bottom_content6.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_7">
					<td colspan="2">
						<div>
							7
							<textarea name="cbdoContent7" rows="5" cols="20">${bottom_content7.cbdContent }</textarea>
							<input type="file" name="cbdoImage7">
							<img src="${conPath }/cookboard_img/${bottom_content7.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_8">
					<td colspan="2">
						<div>
							8
							<textarea name="cbdoContent8" rows="5" cols="20">${bottom_content8.cbdContent }</textarea>
							<input type="file" name="cbdoImage8">
							<img src="${conPath }/cookboard_img/${bottom_content8.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_9">
					<td colspan="2">
						<div>
							9
							<textarea name="cbdoContent9" rows="5" cols="20">${bottom_content9.cbdContent }</textarea>
							<input type="file" name="cbdoImage9">
							<img src="${conPath }/cookboard_img/${bottom_content9.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_10">
					<td colspan="2">
						<div>
							10
							<textarea name="cbdoContent10" rows="5" cols="20">${bottom_content10.cbdContent }</textarea>
							<input type="file" name="cbdoImage10">
							<img src="${conPath }/cookboard_img/${bottom_content10.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_11">
					<td colspan="2">
						<div>
							11
							<textarea name="cbdoContent11" rows="5" cols="20">${bottom_content11.cbdContent }</textarea>
							<input type="file" name="cbdoImage11">
							<img src="${conPath }/cookboard_img/${bottom_content11.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_12">
					<td colspan="2">
						<div>
							12
							<textarea name="cbdoContent12" rows="5" cols="20">${bottom_content12.cbdContent }</textarea>
							<input type="file" name="cbdoImage12">
							<img src="${conPath }/cookboard_img/${bottom_content12.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_13">
					<td colspan="2">
						<div>
							13
							<textarea name="cbdoContent13" rows="5" cols="20">${bottom_content13.cbdContent }</textarea>
							<input type="file" name="cbdoImage13">
							<img src="${conPath }/cookboard_img/${bottom_content13.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_14">
					<td colspan="2">
						<div>
							14
							<textarea name="cbdoContent14" rows="5" cols="20">${bottom_content14.cbdContent }</textarea>
							<input type="file" name="cbdoImage14">
							<img src="${conPath }/cookboard_img/${bottom_content14.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr class="items" id="item_15">
					<td colspan="2">
						<div>
							15
							<textarea name="cbdoContent15" rows="5" cols="20">${bottom_content15.cbdContent }</textarea>
							<input type="file" name="cbdoImage15">
							<img src="${conPath }/cookboard_img/${bottom_content15.cbdImage }" class="img_food">
						</div>
					</td>
				</tr>
				<tr><td colspan="2">
					<button type="button" id="item_plus">(+)</button>
					<button type="button" id="item_minus">(-)</button>
				</td></tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정완료" class="btn">
						<input type="reset" value="취소" class="btn">
						<input type="button" value="뒤로" class="btn" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>