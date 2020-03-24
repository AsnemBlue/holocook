package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.service.Service;

public class MyLikeRecipeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum     = request.getParameter("pageNum"		);
		String my_mId     = request.getParameter("mId"		);
		String keyword     = request.getParameter("keyword"		);
		
		if(pageNum==null) {
			pageNum="1"; 
		}
		if(keyword==null) {
			keyword="";
		}
		//본문 paging
		final int PAGESIZE = 12, BLOCKSIZE = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startrow = (currentPage - 1)*PAGESIZE + 1;
		int endrow	 = startrow + PAGESIZE - 1;
		CookboardDao cbDao = CookboardDao.getInstance();
		request.setAttribute("list", cbDao.cbMyLikeList(keyword, startrow, endrow, my_mId));
		
		//하단 paging
		int totCnt = cbDao.cbMyLikeListCount(my_mId);
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
