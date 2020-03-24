package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardReDao;
import com.hc.holocook.service.Service;

public class CBRDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo 			= Integer.parseInt(request.getParameter("cbNo"));
		int cbrNo 			= Integer.parseInt(request.getParameter("cbrNo"));
		CookboardReDao cbrDao= CookboardReDao.getInstance();
		request.setAttribute("cbNo", cbrDao.cbrDelete(cbrNo, cbNo));
	}

}
