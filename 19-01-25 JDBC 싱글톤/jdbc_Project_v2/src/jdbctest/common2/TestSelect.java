package jdbctest.common2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import employee.model.vo.Employee;

public class TestSelect {

	public static void main(String[] args) {
		Employee emp = new Employee();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String qeury = "select emp_name, hire_date, salary, bonus_pct from employee "
				+ "where emp_id = 100";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(qeury);
			
			if(rset.next()) {
				emp.setEmpName(rset.getString("emp_name"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setSalary(rset.getInt("salary"));
				emp.setBonusPct(rset.getDouble("bonus_pct"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(emp.getEmpName() +", " + emp.getHireDate() + ", " + emp.getSalary() + ", " + emp.getBonusPct());
	}	

}
