package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardReDao;
import com.hc.holocook.service.Service;

public class CBRWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo 			= Integer.parseInt(request.getParameter("cbNo"));
		String mId  		= request.getParameter("mId");
		String cbrContent 	= request.getParameter("cbrContent");
		String cbrIp 		= request.getRemoteAddr();
		CookboardReDao cbrDao= CookboardReDao.getInstance();
		request.setAttribute("cbNo", cbrDao.cbrWrite(cbNo, mId, cbrContent, cbrIp));
	}

}
