package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.dto.MemberDto;
import com.hc.holocook.service.Service;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mNick = request.getParameter("mNick");
		String mName = request.getParameter("mName");
		String mTel = request.getParameter("mTel");
		String mEmail = request.getParameter("mEmail");
		MemberDao mDao = MemberDao.getInstance();
		MemberDto dto = new MemberDto(mId, mPw, mNick, mName, mTel, mEmail, null, null, 0, 0, 1, "요리초보");
		int result = mDao.mJoin(dto);
		if(result==MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			dto = mDao.mGetDto(mId);
			session.setAttribute("mId", mId);
			request.setAttribute("msg_join", "회원가입을 축하합니다!. 로그인 후 이용해 주세요.");
		}else {
			request.setAttribute("err_join", "err_join");
		}
	}

}
