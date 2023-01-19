package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID 값을 입력받아서 입력한 값보다 LPROD_ID가 큰 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("LPROD값 입력 (1~11) : ");
		int input = scan.nextInt();
		
		// DB작업에 필요한 객체 변수 선언 
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 오라클 드라이버 로딩 (생략가능)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
												"KJY09", "java");
			
			String sql = "SELECT LPROD_ID, LPROD_GU, LPROD_NM FROM LPROD WHERE LPROD_ID > " + input;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 쿼리문 처리 결과 ==");
			while(rs.next()) {
			System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
			System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
			System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
			System.out.println("--------------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}

}
