package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.NoticeDao;
import com.hc.holocook.service.Service;

public class NDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeDao nDao = NoticeDao.getInstance(); 
		
		request.setAttribute("dto", nDao.nGetDto(nNo));
	}

}
