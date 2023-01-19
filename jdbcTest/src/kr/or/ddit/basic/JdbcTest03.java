package kr.or.ddit.basic;

import java.lang.management.RuntimeMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.net.aso.p;

// 문제 LPROD_ID 값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오.
public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 LPROD값 입력 (1~11): ");
		int input1 = scan.nextInt();
		System.out.print("두번째 LPROD값 입력 (1~11): ");
		int input2 = scan.nextInt();
		
		// 두 수 비교
//		int temp = 0;
//		if(input1>input2) {
//			temp = input1;
//			input1 = input2;
//			input2 = temp;
//		}
		
		// 두 수 비교 방법1
//		Math.max(input1, input2);
//		Math.min(input1, input2);
		
		// 두 수 비교 방법2
		int max = input1>input2 ? input1 : input2;
		int min = input1<input2 ? input1 : input2;
		
		// DB작업에 필요한 객체 변수를 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 오라클 드라이버 로딩하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"KJY09", "java");
			
//			String sql = "SELECT * FROM LPROD WHERE LPROD_ID > " + input1 + "and LPROD_ID < " + input2;
//			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + input1 + "AND " + input2;

//			stmt =conn.createStatement();
//			rs = stmt.executeQuery(sql);
			
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN ? and ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, min);
			pstmt.setInt(2, max);
			
			rs = pstmt.executeQuery();
			
			System.out.println("=== 쿼리문 처리 결과 ===");
			while(rs.next()) {
				System.out.println("ID : " + rs.getInt("LPROD_ID"));
				System.out.println("GU : " + rs.getString("LPROD_GU"));
				System.out.println("NM : " + rs.getString("LPROD_NM"));
				System.out.println("----------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
			
		}
		
		
	}

}
