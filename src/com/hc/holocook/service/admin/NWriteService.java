package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.NoticeDao;
import com.hc.holocook.dto.NoticeDto;
import com.hc.holocook.service.Service;

public class NWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId 		= request.getParameter("aId");
		String nTitle 	= request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		String nIp 		= request.getRemoteAddr();
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto dto = new NoticeDto(0, nTitle, nContent, 0, null, nIp, aId, null);
		request.setAttribute("result", nDao.nWrite(dto));
	}

}
