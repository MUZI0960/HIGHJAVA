package kr.or.ddit.basic;

import java.lang.management.RuntimeMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import oracle.net.aso.s;

/*
 	LPROD테이블에 새로운 데이터를 추가하기
 	
 	LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고,
 	LPROD_ID는 현재의 LPROD_ID 중에서 제일 큰 값보다 1 크게해서 추가한다.
  	
  	입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리
*/


public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//					"KJY09", "java");
			
			conn = DBUtil.getConnection();
			
			System.out.println("[데이터 추가하기]");
			
			// 내가 생각한 쿼리문...
//			String sql = "INSERT INTO LPROD (LPROD_ID, LPROD_GU, LPROD_NM) "
//						+ " SELECT (SELECT MAX(LPROD_ID) FROM LPROD)+1, "
//						+ " ?, ? "
//						+ " FROM DUAL"
//						+ " WHERE EXISTS (SELECT LPROD_GU FROM LPROD WHERE LPROD_GU=?) ";
			
			String sql = " SELECT MAX(LPROD_ID) max_id FROM LPROD ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			int max_id = 0; 	// 다음 번호의 lprod_id 값이 저장될 변수
			// 레코드가 1개일 때는 if문도 가능
			while(rs.next()) {
				max_id = rs.getInt("max_id");		// alias 이용
//				max_id = rs.getInt(1);				// 컬럼 번호 이용
			}
			max_id++;
			//--------------------------------------------------------------------------
			String gu; 		// 상품분류코드(lprod_gu)가 저장될 변수
			int count = 0; 	// 입력한 상품 분류 코드의 갯수가 저장될 변수
			
			String sql1 = "SELECT COUNT(*) cnt FROM LPROD WHERE LPROD_GU = ? ";
			pstmt = conn.prepareStatement(sql1);

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드 입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count > 0);
			
			System.out.print("상품 분류명 (LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			String sql2 = "INSERT INTO LPROD (LPROD_ID, LPROD_GU, LPROD_NM) "
					+ " VALUES(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql2); // 안닫아주고 또 써서
			pstmt.setInt(1, max_id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("등록 성공 !");
			}else {
				System.out.println("등록 실패 . . .");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
//		}catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
