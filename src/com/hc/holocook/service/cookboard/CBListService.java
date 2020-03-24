package com.hc.holocook.service.cookboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.service.Service;

public class CBListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum     = request.getParameter("pageNum"		);
		String keyword     = request.getParameter("keyword"		);
		String cbcNoOption = request.getParameter("cbcNoOption"	);
		String orderOption = request.getParameter("orderOption"	);
		
		if((pageNum==null) || pageNum.equals("")) {
			pageNum="1"; 
		}
		if(keyword==null) {
			keyword="";
		}
		if(cbcNoOption==null) {
			cbcNoOption="";
		}
		if(orderOption==null) {
			orderOption="cbNo";
		}
		//본문 paging
		final int PAGESIZE = 12, BLOCKSIZE = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startrow = (currentPage - 1)*PAGESIZE + 1;
		int endrow	 = startrow + PAGESIZE - 1;
		CookboardDao cbDao = CookboardDao.getInstance();
		request.setAttribute("list", cbDao.cbList(keyword, cbcNoOption, startrow, endrow, orderOption));
		
		//하단 paging
		int totCnt = cbDao.cbCount(cbcNoOption);
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
