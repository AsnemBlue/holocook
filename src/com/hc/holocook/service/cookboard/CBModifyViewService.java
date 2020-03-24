package com.hc.holocook.service.cookboard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.dao.CookboardDetailDao;
import com.hc.holocook.dao.CookboardReDao;
import com.hc.holocook.dto.CookboardDetailDto;
import com.hc.holocook.dto.CookboardReDto;
import com.hc.holocook.service.Service;

public class CBModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String re_pageNum     = request.getParameter("re_pageNum"	);
		int cbNo = Integer.parseInt(request.getParameter("cbNo"));
		CookboardDao cbDao= CookboardDao.getInstance();
		CookboardDetailDao cbdDao = CookboardDetailDao.getInstance();
		ArrayList<CookboardDetailDto> dtos = cbdDao.cbdGetDtos(cbNo);
		//상단 객체전송
		request.setAttribute("top_content", cbDao.cbGetDto(cbNo));
		
		//요리과정 객체 전송
		for(int i=0; i<dtos.size(); i++) {
			request.setAttribute("bottom_content"+(i+1),dtos.get(i) );
		}
	}

}
