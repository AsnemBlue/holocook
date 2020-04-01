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