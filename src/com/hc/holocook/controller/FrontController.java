package com.hc.holocook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.service.Service;
import com.hc.holocook.service.admin.NDeleteService;
import com.hc.holocook.service.admin.NDetailService;
import com.hc.holocook.service.admin.NListService;
import com.hc.holocook.service.admin.NModifyService;
import com.hc.holocook.service.admin.NModifyViewService;
import com.hc.holocook.service.admin.NWriteService;
import com.hc.holocook.service.admin.QDeleteService;
import com.hc.holocook.service.admin.QDetailService;
import com.hc.holocook.service.admin.QListService;
import com.hc.holocook.service.admin.QModifyService;
import com.hc.holocook.service.admin.QModifyViewService;
import com.hc.holocook.service.admin.QReplyService;
import com.hc.holocook.service.admin.QWriteService;
import com.hc.holocook.service.cookboard.CBDeleteService;
import com.hc.holocook.service.cookboard.CBHitRecipeService;
import com.hc.holocook.service.cookboard.CBListService;
import com.hc.holocook.service.cookboard.CBModifyService;
import com.hc.holocook.service.cookboard.CBModifyViewService;
import com.hc.holocook.service.cookboard.CBRDeleteService;
import com.hc.holocook.service.cookboard.CBRecipeService;
import com.hc.holocook.service.cookboard.CBWriteService;
import com.hc.holocook.service.cookboard.LhChkService;
import com.hc.holocook.service.cookboard.LhUpdateService;
import com.hc.holocook.service.cookboard.MyLikeRecipeService;
import com.hc.holocook.service.cookboard.MyRecipeService;
import com.hc.holocook.service.cookboard.CBRWriteService;
import com.hc.holocook.service.member.MBlackService;
import com.hc.holocook.service.member.MIdChkService;
import com.hc.holocook.service.member.MInfoService;
import com.hc.holocook.service.member.MJoinService;
import com.hc.holocook.service.member.MListService;
import com.hc.holocook.service.member.MLoginService;
import com.hc.holocook.service.member.MLogoutService;
import com.hc.holocook.service.member.MModifyService;
import com.hc.holocook.service.member.MNickChkService;
import com.hc.holocook.service.member.MRankService;
import com.hc.holocook.service.member.MYQListService;
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int writable = 0;	// 새로고침 등록 방지용 변수
    public FrontController() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  uri 	 = request.getRequestURI();
		String  conPath  = request.getContextPath();
		String  command  = uri.substring(conPath.length());
		String  viewPage = null;
		Service service  = null;
		
		
		//메인화면 호출(레시피 리스트 포함)
		if(command.equals("/goMain.do")) {
			service = new CBListService();
			service.execute(request, response);
			viewPage="main/main.jsp";
		} 
		//로그인 화면 호출
		else if(command.equals("/loginView.do")) {
			viewPage = "member/login_view.jsp";
		}
		//로그인 실행
		else if(command.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "goMain.do";
		}
		
		////////////////////////////////////////
		//				회원관련
		////////////////////////////////////////
		//회원가입 뷰 호출
		else if(command.equals("/joinView.do")) {
			writable = 1;
			viewPage = "member/join_view.jsp";
		}
		//회원가입 실행
		else if(command.equals("/join.do")) {
			if(writable==1) {
				service = new MJoinService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "loginView.do";
		}
		//id중복 체크
		else if(command.equals("/idChk.do")) {
			service = new MIdChkService();
			service.execute(request, response);
			viewPage = "member/idChk.jsp";
		}
		//nick중복체크
		else if(command.equals("/nickChk.do")) {
			service = new MNickChkService();
			service.execute(request, response);
			viewPage = "member/nickChk.jsp";
		}
		//개인정보 뷰 보기
		else if(command.equals("/myInfo.do")) {
			service = new MInfoService();
			service.execute(request, response);
			viewPage = "member/myInfo.jsp";
		}
		//개인정보수정뷰 호출
		else if(command.equals("/modifyView.do")) {
			writable = 1;
			viewPage = "member/modify_view.jsp";
		}
		//개인정보 수정
		else if(command.equals("/modify.do")) {
			if(writable==1) {
				service = new MModifyService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "modifyView.do";
		}
		//로그아웃기능
		else if(command.equals("/logout.do")) {
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "goMain.do";
		}
		
		//쉐프랭킹 보기
		else if(command.equals("/chef_rank.do")) {
			service = new MRankService();
			service.execute(request, response);
			viewPage = "member/chef_rank.jsp";
		}
		//회원 리스트 호출
		else if(command.equals("/memberlistView.do")) {
			service = new MListService();
			service.execute(request, response);
			viewPage = "member/memberlist.jsp";
		}
		//블랙하기
		else if(command.equals("/memberBlack.do")) {
			service = new MBlackService();
			service.execute(request, response);
			viewPage = "memberlistView.do";
		}
////////////////////////////////////////
//				레시피 게시판
////////////////////////////////////////
		//레시피 리스트 호출(ajax용)
		else if(command.equals("/listRecipe.do")) {
			service = new CBListService();
			service.execute(request, response);
			viewPage="cookboard/list.jsp";
		}
		//레시피 검색 결과 리스트 페이지
		else if(command.equals("/cookboard.do")) {
			service = new CBListService();
			service.execute(request, response);
			viewPage="cookboard/cookboard.jsp";
		}
		
		//레시피 게시판 조회수 올리기
		else if(command.equals("/hitRecipe.do")) {
			service = new CBHitRecipeService();
			service.execute(request, response);
			viewPage = "recipe.do";
		}
		//레시피 보기(총 정보+요리과정+댓글)
		else if(command.equals("/recipe.do")) {
			service = new CBRecipeService();
			service.execute(request, response);
			viewPage = "cookboard/recipe.jsp";
		}
		//레시피 댓글 쓰기 기능
		else if(command.equals("/recipeReplyWrite.do")) {
			service = new CBRWriteService();
			service.execute(request, response);
			viewPage = "recipe.do";
		}
		//레시피 댓글 삭제
		else if(command.equals("/recipeReplyDelete.do")) {
			service = new CBRDeleteService();
			service.execute(request, response);
			viewPage = "recipe.do";
		}
		//레시피 작성 뷰 호출
		else if(command.equals("/cbWriteView.do")) {
			writable = 1;
			viewPage = "cookboard/cbWrite_view.jsp";
		}
		//레시피 작성
		else if(command.equals("/cbWrite.do")) {
			if(writable==1) {
				service = new CBWriteService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "goMain.do";
		}
		//레시피 수정 뷰 호출
		else if(command.equals("/cbModifyView.do")) {
			service = new CBModifyViewService();
			service.execute(request, response);
			writable = 1;
			viewPage = "cookboard/cbModify_view.jsp";
		}
		//레시피 수정
		else if(command.equals("/cbModify.do")) {
			if(writable==1) {
				service = new CBModifyService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "goMain.do";
		}
		//레시피 삭제
		else if(command.equals("/cbDelete.do")) {
			service = new CBDeleteService();
			service.execute(request, response);
			viewPage = "goMain.do";
		}
		//좋아요 표시
		else if(command.equals("/lh.do")) {
			service = new LhChkService();
			service.execute(request, response);
			viewPage = "cookboard/like.jsp";
		}
		//좋아요 클릭
		else if(command.equals("/lhUpdate.do")) {
			service = new LhUpdateService();
			service.execute(request, response);
			viewPage = "cookboard/like.jsp";
		}
		//레시피 북 호출
		else if(command.equals("/myrecipe.do")) {
			viewPage = "cookboard/recipe_book.jsp";
		}
		//내 레시피북 안에서 ajax용
		//내가 좋아요 누른 레시피 출력
		else if(command.equals("/myLike_List.do")) {
			service = new MyLikeRecipeService();
			service.execute(request, response);
			viewPage = "cookboard/list.jsp";
		}
		//내 레시피북 안에서 ajax용
		//내가 작성한 레시피 출력
		else if(command.equals("/my_List.do")) {
			service = new MyRecipeService();
			service.execute(request, response);
			viewPage = "cookboard/list.jsp";
		}
////////////////////////////////////////
//			 공지사항
////////////////////////////////////////
		//공지사항 게시판 리스트 출력
		else if(command.equals("/notice.do")) {
			service = new NListService();
			service.execute(request, response);
			viewPage = "admin/notice.jsp";
		}
		//공지사항 작성 뷰
		if(command.equals("/nWriteView.do")) {
			writable = 1;
			viewPage = "admin/nWrite_view.jsp";
		}
		//공지사항 작성
		else if(command.equals("/nWrite.do")) {
			if(writable==1) {
				service = new NWriteService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "notice.do";
		}
		//공지사항 상세보기
		else if(command.equals("/noticeDetail.do")) {
			service = new NDetailService();
			service.execute(request, response);
			viewPage = "admin/noticeDetail.jsp";
		}
		//공지사항 수정 뷰
		else if(command.equals("/nModifyView.do")) {
			service = new NModifyViewService();
			service.execute(request, response);
			writable = 1;
			viewPage = "admin/nModify_view.jsp";
		}
		//공지사항 수정
		else if(command.equals("/nModify.do")) {
			if(writable==1) {
				service = new NModifyService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "noticeDetail.do";
		}
		//공지사항 삭제
		else if(command.equals("/nDelete.do")) {
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "notice.do";
		}
////////////////////////////////////////
//				Q n A
////////////////////////////////////////
		//qna 게시판 리스트 출력
		else if(command.equals("/qna.do")) {
			service = new QListService();
			service.execute(request, response);
			viewPage = "admin/qna.jsp";
		}
		//qna 상세보기
		else if(command.equals("/qnaDetail.do")) {
			service = new QDetailService();
			service.execute(request, response);
			viewPage = "admin/qnaDetail.jsp";
		}
		//qna 작성 뷰
		else if(command.equals("/qWriteView.do")) {
			writable = 1;
			viewPage = "admin/qWrite_view.jsp";
		}
		//qna 작성
		else if(command.equals("/qWrite.do")) {
			if(writable==1) {
				service = new QWriteService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "qna.do";
		}
		//qna 수정 뷰
		else if(command.equals("/qModifyView.do")) {
			service = new QModifyViewService();
			service.execute(request, response);
			writable = 1;
			viewPage = "admin/qModify_view.jsp";
		}
		//qna 수정
		else if(command.equals("/qModify.do")) {
			if(writable==1) {
				service = new QModifyService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "qnaDetail.do";
		}
		//qna 삭제
		else if(command.equals("/qDelete.do")) {
			service = new QDeleteService();
			service.execute(request, response);
			viewPage = "qna.do";
		}
		//qna 답변글 뷰
		else if(command.equals("/qReplyView.do")) {
			writable = 1;
			viewPage = "admin/qReply_view.jsp";
		}
		//qna 답변
		else if(command.equals("/qReply.do")) {
			if(writable==1) {
				service = new QReplyService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "qna.do";
		}
		//내가 작성한 qna list보기
		else if(command.equals("/myqna.do")) {
			service = new MYQListService();
			service.execute(request, response);
			viewPage = "member/myqna.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
