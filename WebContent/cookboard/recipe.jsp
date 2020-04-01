<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<style>
tr td:nth-of-type(3) {
line-height: 1.42857143;
font-size: 22px;
box-sizing: border-box;
display: table-cell;
vertical-align: top;
padding-left: 20px;
width: 300px;
}
tr td:nth-of-type(3) img{
box-sizing: border-box;
vertical-align: middle;
border-radius: 10px;
height: auto;
max-width: 300px;
}
#top_content #img_content{
	width:600px;
	height:400px;
	line-height: 400px;
	overflow:hidden;
}
#top_content #img_content img{
	width: 600px;
	vertical-align: middle;
}
#top_content p span{
	color: #f37321;
	font-size: 1.2em;
}
#reply_wrap{
	position: relative;
    width: 900px;
}
#cbrContent{
	width: 500px;
    height: 100px;
}
#btn_Content{
	position: relative;
    bottom: 14px;
}
.reply_list{
	display: flex;
    margin: 10px 0;
    padding: 10px 0;
    border-top: 1px solid #ffd1b3;
}
.reply_left{
	height: 70px;
    width: 70px;
    overflow: hidden;
    border-radius: 70px;
}
.reply_left img{
	display: inline-block;
	width: 70px;
}
.reply_right{
    text-align: left;
	display: inline-block;
    width: 830px;
    padding: 0 15px;
}
.reply_right p{
	text-align: left;
	color: #f37321;
}
.reply_right p span, .reply_right p span a{
	color: #b1acac;
    font-size: 13px;
}
.content{
	width: 800px;
	border-bottom: 1px solid #ffd1b3;
	padding: 10px 0;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		var cbNo = $('input[name="cbNo"]').val();
		var	mId = $('input[name="mId"]').val();
		var	writer = $('input[name="writer"]').val();
		var heartData = {'cbNo':cbNo, 'mId':mId};
		$.ajax({
			url : '${conPath }/lh.do',
			type : 'get',
			data : 'cbNo='+cbNo+"&mId="+mId,
			success : function(data,status){
				$('#lhPlus').html(data);
			}
		})
		$('#lhPlus').click(function(){
			$.ajax({
				url : '${conPath }/lhUpdate.do',
				type : 'get',
				data : 'cbNo='+cbNo+"&mId="+mId+"&writer="+writer,
				success : function(data,status){
					$('#lhPlus').html(data);
				}
			})
/* 			$.get('lhUpdate.do?mId='+mId+'&cbNo='+cbNo, function(data, status) {
			}); */
		}); 
	});
</script>
</head>
<body>
	<form>
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="cbNo" value="${top_content.cbNo }">
		<input type="hidden" name="writer" value="${top_content.mId }">
	</form>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<div id="top_content">
			<div id="img_content">
				<img src="${conPath }/cookboard_img/${top_content.cbImage }">
			</div>
			<P><div class="reply_left"><img src="${conPath }/member_img/${top_content.mPhoto}"></div></P>
			<p><span>${top_content.mNick }</span> 님의</p>
			<p class="content">${top_content.cbTitle }</p>
			<p class="content">${top_content.cbIngredient }</p>
			<p class="content">좋아요 : ${top_content.cbLike } &nbsp; 조회수 : ${top_content.cbHit }</p>
		</div>
		<div id="bottom_content">
			<table>
				<c:set var="cbdCount" value="0" scope="request" />
				<c:forEach items="${bottom_content }" var="dto">
					<tr>
						<td>
							${dto.cbdOrder } :
						</td>
						<td>
							${dto.cbdContent }
						</td>
						<td>
							<img src="${conPath }/cookboard_img/${dto.cbdImage}">
						</td>
					</tr>
					<c:set var="cbdCount" value="${cbdCount+1 }" scope="request" />
				</c:forEach>
			</table>
		</div>
		<c:if test="${not empty member and empty admin }">
			<p class="content"><span id="lhPlus" ></span></p>	
		</c:if>	
		<c:if test="${member.mId eq top_content.mId }">
				<a href="${conPath }/cbModifyView.do?cbNo=${top_content.cbNo}&cbdCount=${cbdCount}" class="btn_normal">수정 </a>
				<a href="${conPath }/cbDelete.do?cbNo=${top_content.cbNo}" class="btn_normal">삭제 </a>
		</c:if>
		<c:if test="${not empty admin}">
				<a href="${conPath }/cbDelete.do?cbNo=${top_content.cbNo}" class="btn_normal">삭제 </a>
		</c:if>
		<div id="reply_wrap">
		<div id="reply">
		<c:if test="${not empty member and empty admin}">
			<form action="${conPath }/recipeReplyWrite.do">
				<input type="hidden" name="cbNo" value="${top_content.cbNo }">
				<input type="hidden" name="mId" value="${member.mId }" id="mId">
				<textarea placeholder="댓글을 남겨주세요." name="cbrContent" id="cbrContent" ></textarea>
				<button name="button" type="submit" class="btn_normal" id="btn_Content">댓글남기기</button>
			</form>
		</c:if>	
			<c:forEach items="${list }" var="dto">
				<div class="reply_list">
					<div class="reply_left">
						<img src="${conPath }/member_img/${dto.mPhoto}">
					</div>
					<div class="reply_right">
						<p>${dto.mNick } 
							<span><fmt:formatDate value="${dto.cbrRdate }" type="both" pattern="yy-MM-dd HH:mm:ss"/>
								<c:if test="${member.mId eq dto.mId }">
									<a href="${conPath }/recipeReplyDelete.do?cbNo=${dto.cbNo}&cbrNo=${dto.cbrNo}"> | 삭제 </a>
								</c:if>
							</span>
						</p>
						<c:if test="${dto.mBlack != 1 }">
							${dto.cbrContent }
						</c:if>
						<c:if test="${dto.mBlack eq 1 }">
							정지된 회원의 글입니다.
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
				<a href="${conPath }/recipe.do?cbNo=${top_content.cbNo}&re_pageNum=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i eq re_pageNum }">
				[ <b> ${i }</b> ]
				</c:if>
				<c:if test="${i != re_pageNum }">
					[ <a href="${conPath }/recipe.do?cbNo=${top_content.cbNo}&re_pageNum=${i }"> ${i }</a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt}">
				<a href="${conPath }/recipe.do?cbNo=${top_content.cbNo}&re_pageNum=${endPage+1 }">다음</a>
			</c:if>
		</div>
	</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>