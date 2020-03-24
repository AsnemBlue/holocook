package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.NoticeDao;
import com.hc.holocook.service.Service;

public class NListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum     = request.getParameter("pageNum"		);
		if((pageNum==null)) {
			pageNum="1";
		}
		//본문
		final int PAGESIZE = 10, BLOCKSIZE = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startrow = (currentPage - 1)*PAGESIZE + 1;
		int endrow	 = startrow + PAGESIZE - 1;
		NoticeDao nDao = NoticeDao.getInstance();
		request.setAttribute("list", nDao.nList(startrow, endrow));
		
		//하단 paging
		int totCnt = nDao.nCount();
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
