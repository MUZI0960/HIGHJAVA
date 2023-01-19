package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC (Java Database Connectivity)를 이용한 DB자료 처리하기
/*
	- 처리순서
	1. 드라이버 로딩 ==> JBDC라이브러리를 사용할 수 있돌고 메모리에 읽어 들이는 작업
		(JDBC API 버전 4.0이상에서는  getConnection()메서드에서 자동으로 로딩해 주기 때문에 
		  이 부분을 생략할 수 있다. 그렇지만 우리는 생략하지 않고 사용할 예정)
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
	2. DB에 접속하기 ==> 접속이 완료되면 Connection객체가 반환된다.
	   DriverManager.getConnection()메서드를 이용한다.
	
	3. 질의  ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
			(Statement객체 PreparedStatement객체를 이용하여 작업한다.)
	
	4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
		1) SQL문이 select문일 때 ==> select한 결과가 ResultSet객체에 저장되어 반환된다.
		2) SQL문이 select문이 아닐 때  (insert문, update문, delete문 등) ==> 작업 결과가 정수값으로 반환된다.
									(반환되는 정수값은 해당 작업에 성공한 레코드 수를 의미한다.)
									
	5. 사용했던 자원 반납하기 ==> 각 자원의 close() 메서드 이용
	
*/
public class JdbcTest01 {

	public static void main(String[] args) {
		// DB작업에 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결  ==> Connection객체 생성
			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@DB서버의 IP주소:1521:xe",
					"jdbc:oracle:thin:@localhost:1521:xe",
					"KJY09","java");
			
			// 3. 질의
			
			// 3-1. SQL문 작성하기
			String sql = "SELECT LPROD_ID, LPROD_GU,LPROD_NM NM FROM LPROD";
			
			// 3-2. Statement객체 또는 PreparedStatement객체 생성 ==> Connection객체 이용
			stmt = conn.createStatement();
			
			// 3-3. SQL문을 DB서버로 보내서 결과를 얻어온다.
			// 		==> 실행할 SQL문이 select문이기 때문에 처리 결과가 ResultSet객체에 저장되어 반환된다.
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리 ==> 한 레코드씩 출력하기
			
			System.out.println(" == 쿼리문 처리 결과 ==");
			// ResultSet객체의 데이터를 차례대로 꺼내오려면 반복문과 next()메서드를 이용하여 처리
			
			// rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 위치로 이동시키고
			//				  그 곳의 데이터가 있으면  true, 없으면 false를 반환한다.
			while(rs.next()) {
				// 포인터가 가리키는 곳의 데이터 가져오기
				// 형식1) rs.get자료형이름("컬럼명 또는 Alias명") ==> 컬럼명과 Alias명은 대소문자 구분없이 사용 가능
				// 형식2) rs.get자료형이름(컬럼번호) ==> 컬럼번호는 1번부터 시작한다.
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_NM : " + rs.getString("NM"));
				System.out.println("--------------------------------------------");
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// 5. 자원 반납
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
	}

}














