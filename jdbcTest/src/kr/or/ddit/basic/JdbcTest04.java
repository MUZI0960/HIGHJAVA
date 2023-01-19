package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
										"KJY09", "java");
			
			System.out.println("[은행 계좌 정보 추가하기]");
			System.out.print("계좌 번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은행 이름 : ");
			String bankName = scan.next();
			
			System.out.print("예금주 이름 : ");
			String userName = scan.next();
			
			/*
			// Statement객체를 이용하여 데이터 추가하기
			String sql = "INSERT INTO BANKINFO (BANK_NO,BANK_NAME,BANK_USER_NAME,BANK_DATE) "
					 + " VALUES ( '" + bankNo + "', '" + bankName + "', '" + userName + "', sysdate) ";
//					 + "VALUES ('111-222-33333-444', '하나은행', '홍길동', sysdate)"
			
			stmt = conn.createStatement();
			
			
			 - SQL문이 'select'문일 경우에는 executeQuery() 메서드를 사용하고,
			   SQL문이 'select'문이 아닐경우 ('insert'문, 'update'문, 'delete'문 등...)는
			 - executeUpdate()메서드 사용
			 - executeUpdate()메서드의 반환값은 '작업에 성공한 레코드 수'이다.
			
			int cnt = stmt.executeUpdate(sql);
			*/
			
			// PreparedStatement 객체를 이용하여 처리
			
			// SQL문을 작성할 때 SQL문의 데이커가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			String sql = "INSERT INTO BANKINFO (BANK_NO,BANK_NAME,BANK_USER_NAME,BANK_DATE) "
					+ " VALUES ( ?, ?, ?, SYSDATE) ";
			
			// prepareStatement객체 생성 ==> 이 때 작성한 SQL문을 인수값으로 넘겨 준다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식 ) pstmt.set자료형이름(물음표번호, 데이터); ==> 물음표 번호는 1부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			// 데이터 셋팅이 완료되면 SQL문을 실행하여 실행 결과를 얻어온다.
			int cnt = pstmt.executeUpdate();
			
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}











