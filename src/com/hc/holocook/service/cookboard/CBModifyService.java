package com.hc.holocook.service.cookboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.holocook.dao.CookboardDao;
import com.hc.holocook.dao.CookboardDetailDao;
import com.hc.holocook.dto.CookboardDetailDto;
import com.hc.holocook.dto.CookboardDto;
import com.hc.holocook.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CBModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("cookboard_img");
		int maxSize = 1024*1024; //1mb
		String[] Images = {"","","","","","","","","","","","","","","",""};
		String cbImage= "";
		int cbdCount = 1 ;
		try {
			//mrequest 객체 생성후 파일이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			cbImage = mRequest.getFilesystemName("cbImage");
			String origin_cbImage 		= mRequest.getParameter("origin_cbImage");
			cbImage = cbImage!=null? cbImage: origin_cbImage;	//대표 이미지 수정등록 안할시 원래 대표 이미지.
			//cbWrite 용 파라미터값 다 받아오기->db에 넣기
			cbdCount 			= Integer.parseInt(mRequest.getParameter("cbdCount"));
			int    cbNo 		= Integer.parseInt(mRequest.getParameter("cbNo"	));
			int    cbcNo 		= Integer.parseInt(mRequest.getParameter("cbcNo"));
			String cbTitle 		= mRequest.getParameter("cbTitle"		);
			String cbIngredient = mRequest.getParameter("cbIngredient"	);
			String mId 			= mRequest.getParameter("mId"			);
			String cbIp			=request.getRemoteAddr();
			CookboardDao cbDao = CookboardDao.getInstance();
			CookboardDto dto = new CookboardDto(cbNo, cbTitle, cbImage, cbIngredient, 0, null, cbIp, cbcNo, mId, null, null, null);
			int result = cbDao.cbModify(cbNo, cbTitle, cbImage, cbIngredient, cbIp, cbcNo);
			if(result==cbDao.SUCCESS) {
				request.setAttribute("cbWriteResult", "레시피등록이 완료되었습니다.");
				request.setAttribute("cbNo", cbNo);
			}else {
				request.setAttribute("errorMsg", "등록 실패했습니다.");
			}
			//요리과정 + 이미지 받아오기.
			CookboardDetailDao cbdDao = CookboardDetailDao.getInstance();
			ArrayList<CookboardDetailDto> cbdDtos = cbdDao.cbdGetDtos(cbNo);
			for(int i=1;i<=12 ;i++) {
				String cbdContent = mRequest.getParameter("cbdoContent"+i);
				String cbdImage   = mRequest.getFilesystemName("cbdoImage"+i);
				String lastImage  = cbdDao.cbdGetImage(cbNo, i);
				cbdImage = cbdImage!=null? cbdImage:lastImage;
				int cbdOrder = i;
				int cbdresult = cbdDao.cbdModify(cbdOrder, cbdImage, cbdContent, cbNo);
				if(cbdresult==cbdDao.SUCCESS) {
					request.setAttribute("cbdWriteResult", "레시피등록이 완료되었습니다.");
				}else {
					request.setAttribute("errorMsg", "요리 과정 등록 실패했습니다.");
				}
			}
			//소스폴더 복사용
			int idx = 0;
			while(paramNames.hasMoreElements()){
				String param = paramNames.nextElement(); //파라미터 이름
				Images[idx] = mRequest.getFilesystemName(param); // 서버에 저장된 파일이름
				idx++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		int idx = 1;
		for(String f : Images){
			File serverFile = new File(path+"/"+f);
			if(f!=null){
				if(!f.equals("default.png") && serverFile.exists()) {
					InputStream is  = null;
					OutputStream os = null;
					try{
						if(serverFile.exists()){
							is = new FileInputStream(serverFile);
							os = new FileOutputStream("D:/mega_IT/source/7_jQuery/holocook/WebContent/cookboard_img/"+f);
							byte[] bs = new byte[(int)serverFile.length()];
							while(true){
								int nReadCnt = is.read(bs); // nReadCnt : 읽어온 바이트 수
								if(nReadCnt==-1){
									break;
								}
							os.write(bs, 0, nReadCnt);
							}
						}
					}catch(IOException e){
					System.out.println(e.getMessage());
					}finally{
						try{
							if(os!=null) os.close();
							if(is!=null) is.close();
						}catch(IOException e){ System.out.println(e.getMessage()); }
					}
				}
			}
			idx++;
			if(idx>cbdCount) {
				break;
			}
		}//for
	}
}
