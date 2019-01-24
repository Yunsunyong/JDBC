package jdbctest.common2;

import java.sql.*;

public class TestInsert {
	
	public static void main(String[] args) {
		int result = 0;
		Member m = new Member();
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print("멤버 이름 :");
		m.setmName(sc.next());
		System.out.print("멤버 월급 :");
		m.setSalary(sc.nextInt());
		
		Connection conn = null;
		Statement stmt = null;
		
		String qeury = "insert into member values (seq_mem.nextval, '"
				+ m.getmName()+"', "
				+ m.getSalary()+")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(qeury);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
