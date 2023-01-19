package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 - ResourceBundle 객체 ==> 파일의 확장자가 '.propertise'인 파일의 내용을 읽어와
  						 key값과 value값을 분리해서 정보를 갖는 객체
 			==> 읽어올 파일의 내용은 'key값 = value값' 형태로 구성되어 있어야 한다.
 */

public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle객체를 이용해서 파일 읽어오기
		
		// ResourceBundle객체 생성
		// ==> 읽어올 파일을 지정할 때 '패키지명.파일명'만 지정하고 확장자는 지정하지 않는다.
//				(이유 : 확장자는 항상 '.properties'이기 때문이다.)
		ResourceBundle bundle = 
				ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");

		// 읽어온 파일 내용 확인하기
		System.out.println("driver : " + bundle.getString("driver"));
		System.out.println("url : " + bundle.getString("url"));
		System.out.println("user : " + bundle.getString("user"));
		System.out.println("pass : " + bundle.getString("pass"));
		
	}

}














