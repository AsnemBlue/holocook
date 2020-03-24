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
<link href="${conPath }/css/main/header.css" rel="stylesheet">
<style>
li{
    background-color: white;
}
header{
	position: relative;
    z-index: 100;
}
#searchWrap #btn_writeRecipe{
	height: 30px;
    position: relative;
    top: 10px;
    left: 300px;
}
#customer_center_list, #cook_center_list{
	position: absolute;
    top: 35px;
    left: 0px;
    z-index: 999;
    background: rgb(255, 255, 255);
    width: 100%;
    display: none;
}
.lineMenu_list{
    border-bottom: 3px solid rgb(243, 115, 33);
}
hr{
	width: 100%;
}
#btn_admin{    
	border-bottom: 1px solid white;
    height: 30px;
    position: relative;
    top: 5px;
}
#toggle_admin{
    position: relative;
    top: 50px
}
</style>
<script>
    $(document).ready(function(){
    	var togCnt = 0;
    	$('.toggle_menu').hide();
    	$('.toggle_button').click(function(){
        	if(togCnt % 2 == 0){
        		 $('.toggle_menu').fadeIn("slow");
        	}else{
        		 $('.toggle_menu').fadeOut("fast");
        	}
        	togCnt++;
    	});
    	//홀로쿡 오버 이벤트
        $('#cook_center').mouseover(function(){
        	$('#cook_center_list').stop().slideDown();
        });
        $('#cook_center').mouseout(function(){
        	$('#cook_center_list').stop().slideUp();
        });
        //고객센터 오버이벤트
        $('#customer_center').mouseover(function(){
        	$('#customer_center_list').stop().slideDown();
        });
        $('#customer_center').mouseout(function(){
        	$('#customer_center_list').stop().slideUp();
        });
        
    });
</script>
</head>
<body>
	<header>
		<div id="searchlogin">
			<div id="searchWrap">
				<div id="search">
					<form action="${conPath }/cookboard.do"> 
						<input type="hidden" name="option" value="search_direct">
						<input type="text" placeholder="음식명 또는 재료명 " name="keyword" value="${param.keyword }" id="input_keyword" class="text_search">
						<input type="submit" value="" class="btn_search">
					</form>
				</div>
				<div id="loginwrap">
				<!--quest 전용 메뉴  -->
				<c:if test="${empty member and empty admin}">
					<div id="btn_login_view">
						<a href="${conPath }/loginView.do">
							<span id="btn_login_view_text">로그인</span>
						</a> 
					</div>
				</c:if>
				<!--로그인 사용자 전용 메뉴  -->
				<c:if test="${not empty member and empty admin}">
				<div id="login_button_wrap">
					<a href="${conPath }/cbWriteView.do">
					<img id="btn_writeRecipe" src="${conPath }/img/header/pencil.png" title="레시피 작성"></a>
					<button type="button" id="login_button" class="toggle_button">
						<img src="${conPath }/member_img/${member.mPhoto }" >
					</button>
				</div>
				<ul id="floatmenu" class="toggle_menu"> 
					<li><a href="myInfo.do?mId=${member.mId }">내정보</a></li>
					<li><a href="${conPath }/myrecipe.do?mId=${member.mId }">레시피 북</a></li>
					<li><a href="myqna.do?mId=${member.mId }">내 QnA</a></li>
					<li><a href="modifyView.do">정보수정</a></li>
					<li><a href="logout.do">로그아웃</a></li>
				</ul>
				<!--admin 전용 메뉴  -->
				</c:if>
				<c:if test="${empty member and not empty admin}">
					<button type="button" class="toggle_button" id="btn_admin">
						admin
					</button>
					<ul class="toggle_menu" id="toggle_admin">
						<li><a href="memberlistView.do">회원리스트</a></li>
						<li><a href="nWriteView.do">공지작성</a></li>
						<li><a href="logout.do">로그아웃</a></li>
					</ul>
				</c:if>
				</div>
			</div>
		</div>
		<nav>
			<ul id="lineMenu">
				<li><a href="${conPath }/goMain.do">Home</a></li>
				<li id="cook_center">
					<a>홀로쿡</a>
					<ul id="cook_center_list" class="lineMenu_list">
						<li><a href="${conPath }/cookboard.do?cbcOption=1">알뜰쿠킹</a></li>
						<li><a href="${conPath }/cookboard.do?cbcOption=2">욜로쿠킹</a></li>
					</ul>
				</li>
				<li><a href="${conPath }/chef_rank.do">쉐프랭킹</a></li>
				
				<li id="customer_center">
					<a>고객센터</a>
					<ul id="customer_center_list" class="lineMenu_list">
					<li><a href="${conPath }/notice.do">공지사항</a></li>
					<li><a href="${conPath }/qna.do">QnA</a></li>
					</ul>
				</li>
			</ul>
			
		</nav>
	<hr>
	</header>
</body>
</html>