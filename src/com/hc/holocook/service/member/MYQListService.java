package com.hc.holocook.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.QnaDao;
import com.hc.holocook.service.Service;

public class MYQListService implements Service {

	@Override 
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum     = request.getParameter("pageNum"	);
		String mId     = request.getParameter("mId"	);
		if((pageNum==null) || pageNum.equals("")) {
			pageNum="1";
		}
		
		//본문
		final int PAGESIZE = 10, BLOCKSIZE = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startrow = (currentPage - 1)*PAGESIZE + 1;
		int endrow	 = startrow + PAGESIZE - 1;
		QnaDao qDao = QnaDao.getInstance();
		request.setAttribute("list", qDao.myqList(mId, startrow, endrow));
				
		//하단 paging
		int totCnt = qDao.myqCount(mId);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage - 1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = (startPage + BLOCKSIZE)-1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE	);
		request.setAttribute("startPage", startPage	);
		request.setAttribute("endPage"	, endPage	);
		request.setAttribute("pageNum"	, pageNum	);
		request.setAttribute("pageCnt"	, pageCnt	);

	}

}
