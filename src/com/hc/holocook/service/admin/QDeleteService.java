package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.QnaDao;
import com.hc.holocook.service.Service;

public class QDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum     = request.getParameter("pageNum"		);
		int qNo     = Integer.parseInt(request.getParameter("qNo"));
		QnaDao qDao = QnaDao.getInstance();
		int result = qDao.qDelete(qNo);
		if(result==QnaDao.SUCCESS) {
			request.setAttribute("result", "삭제가 완료 되었습니다.");
		}else {
			request.setAttribute("result", "삭제를 실패하였습니다.");
		}
	}

}
