package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.dao.CookboardDetailDao;
import com.hc.holocook.dao.CookboardReDao;
import com.hc.holocook.service.Service;

public class CBRecipeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String re_pageNum   = request.getParameter("re_pageNum"	);
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		CookboardDao cbDao= CookboardDao.getInstance();
		CookboardDetailDao cbdDao = CookboardDetailDao.getInstance();
		
		//상단 객체전송
		request.setAttribute("top_content", cbDao.cbGetDto(cbNo));
		
		//요리과정 객체 전송
		request.setAttribute("bottom_content", cbdDao.cbdGetDtos(cbNo));
		
		//댓글 객체 전송
		if((re_pageNum==null) || re_pageNum.equals("")) {
			re_pageNum="1";
		}
		//본문 paging
		final int PAGESIZE = 5, BLOCKSIZE = 5;
		int currentPage = Integer.parseInt(re_pageNum);
		int startrow = (currentPage - 1)*PAGESIZE + 1;
		int endrow	 = startrow + PAGESIZE - 1;
		CookboardReDao cbrDao = CookboardReDao.getInstance();
		request.setAttribute("list", cbrDao.cbrGetDtos(cbNo, startrow, endrow));
	
		//하단 paging
		int totCnt = cbrDao.cbrCount(cbNo);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage - 1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = (startPage + BLOCKSIZE)-1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE	);
		request.setAttribute("startPage", startPage	);
		request.setAttribute("endPage"	, endPage	);
		request.setAttribute("re_pageNum"	, re_pageNum	);
		request.setAttribute("pageCnt"	, pageCnt	);
	}

}
