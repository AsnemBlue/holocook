package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.service.Service;

public class MIdChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		
		if(mDao.mIdChk(mId)==MemberDao.NONEXISTENT) {
			request.setAttribute("idChkMsg","사용가능한 ID입니다." );
		}else {
			request.setAttribute("idChkMsg","중복된 ID입니다." );
		}
				
	}

}
