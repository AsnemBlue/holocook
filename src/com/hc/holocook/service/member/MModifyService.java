package com.hc.holocook.service.member;

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

import com.hc.holocook.dao.MemberDao;
import com.hc.holocook.dto.MemberDto;
import com.hc.holocook.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("member_img");
		int maxSize = 1024*1024; //1mb
		String mPhoto = "";
		try {
			//mrequest 객체 생성후 파일이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			mPhoto = mRequest.getFilesystemName(param);
			// 정보수정시 사진파일을 첨부안하면 originPhoto(원래 사진파일)로
			String originPhoto = mRequest.getParameter("originPhoto");
			mPhoto = mPhoto==null? originPhoto :mPhoto;
			//파라미터값 다 받아오기->db에 넣기
			String mId 			= mRequest.getParameter("mId"		);
			String mPw 			= mRequest.getParameter("mPw"		);
			String mNick 		= mRequest.getParameter("mNick"		);
			String mName 		= mRequest.getParameter("mName"		);
			String mTel 		= mRequest.getParameter("mTel"		);
			String mEmail 		= mRequest.getParameter("mEmail"	);
			MemberDao mDao = MemberDao.getInstance();
			MemberDto dto = new MemberDto(mId, mPw, mNick, mName, mTel, mEmail, mPhoto, null, 0, 0, 0, null);
			int result = mDao.mModify(dto);
			if(result==mDao.SUCCESS) {
				HttpSession session = request.getSession();
				request.setAttribute("modifyResult", "정보 수정이 완료되었습니다.");
				session.setAttribute("member", dto);
			}else {
				request.setAttribute("errorMsg", "입력 내용이 너무 길어 수정 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		//업로드된 파일을 소스폴더로 복사
		File serverFile = new File(path+"/"+mPhoto);
		if(!mPhoto.equals("default.png") && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/7_jQuery/holocook/WebContent/member_img/"+mPhoto);
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
