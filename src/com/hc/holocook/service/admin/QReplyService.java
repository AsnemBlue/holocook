package com.hc.holocook.service.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hc.holocook.dao.QnaDao;
import com.hc.holocook.dto.QnaDto;
import com.hc.holocook.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("qna_file");
		int maxSize = 1024*1024*100; //100mb
		String qFile = "";
		int result = -1;
		try {
			//mrequest 객체 생성후 파일이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			QnaDao qDao = QnaDao.getInstance();
			int qNo = Integer.parseInt(mRequest.getParameter("qNo"));
			QnaDto dto = qDao.qGetDto(qNo);
			qFile = mRequest.getFilesystemName(param);
			//파라미터값 다 받아오기->db에 넣기
			dto.setqTitle	(mRequest.getParameter("qTitle"		));
			dto.setqContent	(mRequest.getParameter("qContent"	));
			dto.setqIp		(request.getRemoteAddr()             );
			dto.setaId		(mRequest.getParameter("aId"		));
			dto.setmId		(mRequest.getParameter("mId"		));
			int resultA = qDao.qStepA(dto);
			if(resultA==0) {
				result = qDao.qStepB(dto);
			}else if(resultA>0) {
				qDao.qStepBeforeC(dto, resultA);
				result = qDao.qStepC(dto, resultA);
			}else {
				result = QnaDao.FAIL;
			}
			
			if(result==QnaDao.SUCCESS) {
				HttpSession session = request.getSession();
				request.setAttribute("result", "글 작성이 완료되었습니다.");
			}else {
				request.setAttribute("error", "첨부파일 용량을 초과 하였습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		//업로드된 파일을 소스폴더로 복사
		File serverFile = new File(path+"/"+qFile);
		if(serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/7_jQuery/holocook/WebContent/qna_file/"+qFile);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int readbyteCnt = is.read(bs);
					if(readbyteCnt == -1) break;
					os.write(bs,0,readbyteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (IOException e) {}
			}
		}
	}

}
