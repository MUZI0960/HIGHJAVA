package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

/*
	- Servlet 3.0 이상에서 파일 업로드를 하려면 서블릿에 @MultipartConfig 애노테이션을 설정해야 한다.
	
	- @MultipartConfig 애노테이션에서 설정하는 변수들
	1) location : 업로드한 파일이 임시적으로 저장될 경로를 지정한다. (기본값 : "")
	2) fileSizeThreshold : 이 곳에 설정한 값보다 큰 파일이 전송되면 location에 지정한 
		임시 디렉토리에 데이터를 저장한다. (단위 : byte, 기본값 : 0 -> 무조건 임시디렉토리를 사용)
	3) maxFilesize : 1개의 파일의 최대 크기 (단위 : byte, 기본값 : -1L -> 무제한)
	4) maxRequestSize : 서버로 전송되는 Request데이터 전체의 최대 크기
		(모든 파일 크기의 합 + formData) (단위 : byte, 기본값 : -1L -> 무제한)
*/

@WebServlet("/fileUpload.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024* 1024 * 30,
		maxRequestSize = 1024 * 1024 * 100
		)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로 요청하면 FileUpload 폼이 있는 jsp문서를 보여준다.
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드된 파일이 저장될 폴더 설정 (서버컴퓨터쪽)
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 존재하지 않으면 새로 생성한다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구하면 된다.
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터 데이터 username => " + userName);
		
		//----------------------------------------------------------
		
		// 수신 받은 데이터 처리하기
		String fileName = ""; 	// 수신된 파일의 파일명이 저장될 변수
		
		// Upload된 파일이 여러 개 일 경우 전체 파일 정보를 저장할 List 객체 생성
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		/*
		- Servlet 3.0이상에서 새롭게 추가된 Upload용 메서드
		1) request.getParts() ==> 전체 Part객체를 Collection객체에 담아서 반환 
		2) request.getPart("part이름") ==> 지정된 'part이름'을 가진 개별 Part객체 1개를 반환한다.
			'part이름' ==> <form>태그 안의 입력요소의 name속성값으로 구별한다.
		*/
		
		// 전체 Part객체 갯수만큼 반복 처리
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);	
			
			// 찾은 파일명이 공백("")이면 이것은 파일이 아닌 일반 파라미터 데이터라는 의미이다.
			if(!"".equals(fileName)) { // 파일인지 검사
				// 1개의 Upload파일에 대한 정보를 저장할 VO객체 생성
				FileInfoVO fvo = new FileInfoVO();
				fvo.setFile_writer(userName); 	// 작성자를 VO에 셋팅
				fvo.setOrigin_file_name(fileName);	// 원래의 파일명을 VO에 셋팅
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID객체를 이용하여 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString();
				fvo.setSave_file_name(saveFileName);	// 만들어진 저장 파일명을 VO에 셋팅
				
				// 전송된 파일의 크기는 Part객체의 getSize()메서드를 이용해서 구한다. (단위 : byte)
				// byte단위의 파일 크기를 KB단위로 변환해서 VO에 셋팅
				fvo.setFile_size( (long)Math.ceil(part.getSize()/1024.0));
				
				try {
					// Upload된 파일 저장하기
					// 형식) Part객체변수.write("경로명/저장할파일명");
					part.write(uploadPath + File.separator + saveFileName);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Upload된 파일 정보가 저장된 VO객체를 List에 추가
				fileList.add(fvo);
				
			} // if문 끝
			
		} // for문 끝
		
		// Upload된 파일 정보를 DB에 추가하기
		
		// Service 객체 생성
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		// List에 저장된 파일 정보를 DB에 insert한다.
		for(FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// insert가 모두 완료되면 저장된 파일 목록을 보여준다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
	}	// doPost메서드 끝
	
	/*
		- Part의 구조
		1) 파일이 아닌 일반 데이터 일 경우
		-------------akhrfalweitg1245 ==> Part를 구분하는 구분선
		content-disposition : form-data; name="username" 	==>파라미터명
						==> 빈줄
		홍길동			==> 파라미터 값
		
		2) 파일일 경우
		-------------akhrfalweitg1245 ==> Part를 구분하는 구분선
		content-disposition: form-data; name="upFile1"; filename="test.txt" 	==> 파일 정보
		content-type: text/plain		==> 파일의 종류
						==> 빈줄
		12345abcd안녕 	==> 파일의 내용
	*/
	// Part구조 안에서 전송된 파일의 실제 파일명 찾는 메서드
	private String extractFileName(Part part) {
		String filename = "";
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";"); 
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				filename = item.substring(item.indexOf("=")+2,item.length()-1);
			}
		}
		
		return filename;
	}

}
