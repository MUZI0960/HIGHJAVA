package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

// 서버의 웹 영역 바깥에 있는 이미지 파일을 읽어서 보내주는 서블릿
@WebServlet("/imageView.do")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어오는 파일 번호를 받는다.
		int fileNo = Integer.parseInt(request.getParameter("fileNo"));
		
		// 파일 번호를 이용해서 DB에 있는 파일 정보 가져오기
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		FileInfoVO fvo = service.getFileInfo(fileNo);
		
		// 이미지가 저장된 폴더 경로 지정
		String filePath = "d:/d_other/uploadFiles";
		
		// 파일 정보에서 실제 저장된 파일명을 이용하여 File객체를 생성
		File file = new File(filePath + File.separator + fvo.getSave_file_name());
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		if(file.exists()) { // 해당 이미지 파일이 있을 경우
			try {
				bin = new BufferedInputStream(new FileInputStream(file));
				bout = new BufferedOutputStream(response.getOutputStream());
				
				byte[] temp = new byte[1024];
				int len = 0;
				while((len=bin.read(temp))>0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				if(bin!=null) try { bin.close(); }catch(IOException e) {}
				if(bout!=null) try { bout.close(); }catch(IOException e) {}
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
