package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hc.holocook.dao.AdminDao;
import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.dto.AdminDto;
import com.hc.holocook.dto.MemberDto;
import com.hc.holocook.service.Service;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		
		//admin으로 시작하는 계정->admin login
		if(mId.indexOf("관리자")==0) {	
			AdminDao aDao = AdminDao.getInstance();
			int result = aDao.aLogin(mId, mPw);
			if(result==AdminDao.SUCCESS) {
				HttpSession session = request.getSession();
				AdminDto dto = aDao.aGetDto(mId);
				session.setAttribute("admin", dto);
			}else {
				request.setAttribute("errorMsg", "아이디와 비밀번호를 확인해 주세요");
			}
		//그외 계정 -> member login	
		}else{
			MemberDao mDao = MemberDao.getInstance();
			int result = mDao.mLogin(mId, mPw);
			if(result==MemberDao.SUCCESS) {
				HttpSession session = request.getSession();
				MemberDto dto = mDao.mGetDto(mId);
				session.setAttribute("member", dto);
			}else {
				request.setAttribute("errorMsg", "아이디와 비밀번호를 확인해 주세요");
			}
		}
	}

}
