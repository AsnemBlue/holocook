package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.service.Service;

public class CBHitRecipeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		CookboardDao cbDao= CookboardDao.getInstance();
		cbDao.cbHitUp(cbNo);
		request.setAttribute("cbNo", cbNo);
	}

}
