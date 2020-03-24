package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.LikeHistoryDao;
import com.hc.holocook.dto.LikeHistoryDto;
import com.hc.holocook.service.Service;

public class LhChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		String mId = request.getParameter("mId");
		LikeHistoryDao dao = LikeHistoryDao.getInstance();
		int result = dao.lhChk(cbNo, mId);
		if(result == LikeHistoryDao.UNLIKED) {
			request.setAttribute("result", "black");
		}else if(result == LikeHistoryDao.LIKED){
			request.setAttribute("result", "red");
		}
	}

}
