package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.LikeHistoryDao;
import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.service.Service;

public class LhUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		String mId = request.getParameter("mId");
		String writer = request.getParameter("writer");
		MemberDao mdao = MemberDao.getInstance();
		LikeHistoryDao dao = LikeHistoryDao.getInstance();
		int tempResult = dao.lhChk(cbNo, mId);
		
		if(tempResult == LikeHistoryDao.UNLIKED) {
			int result = dao.lhPlus(cbNo, mId);
			//좋아요 히스토리에 기록이 없는 상태 ->좋아요 상승
			mdao.mLikeUp(writer);
			request.setAttribute("result", "red");
		}else if(tempResult == LikeHistoryDao.LIKED) {
			int result = dao.lhMinus(cbNo, mId);
			//좋아요 히스토리에 기록이 있는 상태 -> 좋아요 하강
			mdao.mLikeDown(writer);
			//
			request.setAttribute("result", "black");
		}

	}

}
