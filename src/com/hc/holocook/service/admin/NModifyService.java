package com.hc.holocook.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.NoticeDao;
import com.hc.holocook.dto.NoticeDto;
import com.hc.holocook.service.Service;

public class NModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String 	nTitle 	= request.getParameter("nTitle");
		String 	nContent= request.getParameter("nContent");
		String 	nIp 	= request.getRemoteAddr();
		String 	aId 	= request.getParameter("aId");
		int 	nNo 	= Integer.parseInt(request.getParameter("nNo"));
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto dto = new NoticeDto(nNo, nTitle, nContent, 0, null, nIp, aId, null);
		int result = nDao.nModify(dto);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("result", "공지 수정이 완료되었습니다.");
		}else {
			request.setAttribute("error", "공지 수정을 실패했습니다.");
			
		}
	}

}
