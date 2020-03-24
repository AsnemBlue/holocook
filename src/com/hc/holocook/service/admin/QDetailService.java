package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.QnaDao;
import com.hc.holocook.service.Service;

public class QDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QnaDao qDao = QnaDao.getInstance();
		 
		request.setAttribute("dto", qDao.qGetDto(qNo));

	}

}
