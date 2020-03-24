package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.dao.CookboardDetailDao;
import com.hc.holocook.dao.CookboardReDao;
import com.hc.holocook.dao.LikeHistoryDao;
import com.hc.holocook.service.Service;

public class CBDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		CookboardDao cbDao = CookboardDao.getInstance();
		CookboardDetailDao cbdDao = CookboardDetailDao.getInstance();
		CookboardReDao cbrDao = CookboardReDao.getInstance();
		LikeHistoryDao lhDao = LikeHistoryDao.getInstance();
		int lhResult = lhDao.lhDelete(cbNo);
		int cbrResult = cbrDao.cbrDeleteAll(cbNo);
		int cbdResult = cbdDao.cbdDelete(cbNo);
		int cbResult = cbDao.cbDelete(cbNo);
		request.setAttribute("deleteResult", cbResult*cbdResult*cbrResult*lhResult);
	}

}
