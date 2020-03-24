package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.service.Service;

public class MBlackService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId     = request.getParameter("mId"	);
		MemberDao mDao = MemberDao.getInstance();
		request.setAttribute("list", mDao.mBlack(mId));
		
	}

}
