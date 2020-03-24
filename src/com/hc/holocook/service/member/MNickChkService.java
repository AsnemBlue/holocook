package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.service.Service;

public class MNickChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mNick = request.getParameter("mNick");
		MemberDao mDao = MemberDao.getInstance();
		
		if(mDao.mNickChk(mNick)==MemberDao.NONEXISTENT) {
			request.setAttribute("nickChkMsg","사용가능한 닉네임입니다." );
		}else {
			request.setAttribute("nickChkMsg","중복된 닉네임입니다." );
		}

	}

}
