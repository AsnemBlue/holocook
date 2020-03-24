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

public class QWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("qna_file");
		int maxSize = 1024*1024*100; //100mb
		String qFile = "";
		try {
			//mrequest 객체 생성후 파일이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			qFile = mRequest.getFilesystemName(param);
			//파라미터값 다 받아오기->db에 넣기
			String qTitle	= mRequest.getParameter("qTitle"		);
			String qContent	= mRequest.getParameter("qContent"	);
			String qIp		= request.getRemoteAddr();
			String aId		= mRequest.getParameter("aId"		);
			String mId		= mRequest.getParameter("mId"		);
			QnaDao qDao = QnaDao.getInstance();
			QnaDto dto = new QnaDto(0, qTitle, qContent, 0, 0, 0, 0, qFile, 0, null, qIp, aId, null, mId, null, null);
			int result = qDao.qWrite(dto);
			if(result==qDao.SUCCESS) {
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
