package employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import employee.model.vo.Employee;

//dao : database access object
//jdbc 코드 : 비즈니스 로직
//데이터베이스에 대한 CRUD 소스 구현
//C(Create : insert), R(Read : select)
//U(Update : update), D(Delete : delete)
public class EmployeeDao {

	//기본생성자
	public EmployeeDao() {}
	
	//직원 전체 조회 처리용 메소드
	public ArrayList<Employee> selectList() {
		ArrayList<Employee> empList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from employee";
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 등록 성공!");
			
			//2. 데이터베이스(DBMS) 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			//System.out.println("conn : " + conn);
			
			//3. 쿼리문 가지고 db에 가서 실행하고 나서
			//결과 가지고 돌아오는 객체 생성함
			stmt = conn.createStatement(); //꼭 닫아야함
			
			//4. 쿼리문 보내서 실행하고 결과받기
			rset = stmt.executeQuery(query);
			
			//5.  ResultSet 에 조회해 온 결과 행들의 컬럼값들을
			//하나씩 꺼내서 변수|필드에 옮겨 담기
			while(rset.next()) {
				Employee emp = new Employee(); //직원 한사람의 정보를 저장할 객체
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("emp_name"));
				emp.setEmpNo(rset.getString("emp_no"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setJobId(rset.getString("job_id"));
				emp.setSalary(rset.getInt("salary"));
				emp.setBonusPct(rset.getDouble("bonus_pct"));
				emp.setMarriage(rset.getString("marriage"));
				emp.setMgrId(rset.getString("mgr_id"));
				emp.setDeptId(rset.getString("dept_id"));
				
				empList.add(emp);
			}
		} catch (ClassNotFoundException e) {
			//1. ojdbc6.jar 를 등록 안할시
			//2. Class.forName(""); 클래스네임이 오타날경우
			e.printStackTrace();
		} catch (SQLException e) {
		 	e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public Employee selectOne(String empId) {
		Employee emp = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = 
				"select * from employee where emp_id = '" + empId +"'";
				
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. 데이터베이스 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","student","student");
		//3. 쿼리문 가지고 db에 가서 실행하고 나서
		//결과 가지고 돌아오는 객체 생성
			stmt = conn.createStatement();
		//4. 쿼리문 보내서 실행하고 결과받기
			rset = stmt.executeQuery(query);
		//5. ResultSet 에 조회해 온 결과 행들의 컬럼값들을
		//하나씩 꺼내서 변수 | 필드에 옮겨 담기
			if(rset.next()) {
				emp = new Employee(); //직원 한사람의 정보를 저장할 객체
				emp.setEmpId(empId);
				emp.setEmpName(rset.getString("emp_name"));
				emp.setEmpNo(rset.getString("emp_no"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setJobId(rset.getString("job_id"));
				emp.setSalary(rset.getInt("salary"));
				emp.setBonusPct(rset.getDouble("bonus_pct"));
				emp.setMarriage(rset.getString("marriage"));
				emp.setMgrId(rset.getString("mgr_id"));
				emp.setDeptId(rset.getString("dept_id"));	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	public ArrayList<Employee> selectJobList(String jobId) {
		ArrayList<Employee> empList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from employee where job_id = '" 
		+ jobId +"'"; 
		
		try {
			//1. 드라이버 등록
				Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.데이터베이스 연결
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			//3.쿼리문 가지고 DB에서 가서 실행하고 나서
			//결과 가지고 돌아오는 객체 생성함
				stmt = conn.createStatement();
			//4. 쿼리문 보내서 실행하고 결과받기
				rset = stmt.executeQuery(query);
			//5.ResultSet 에 조회해 온 결과 행들의 컬럼값들을
			//하나씩 꺼내서 변수 | 필드에 옮겨 담기
			while (rset.next()) {
				Employee emp = new Employee();
				emp.setEmpId("emp_id");
				emp.setEmpName(rset.getString("emp_name"));
				emp.setEmpNo(rset.getString("emp_no"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setJobId(jobId);
				emp.setSalary(rset.getInt("salary"));
				emp.setBonusPct(rset.getDouble("bonus_pct"));
				emp.setMarriage(rset.getString("marriage"));
				emp.setMgrId(rset.getString("mgr_id"));
				emp.setDeptId(rset.getString("dept_id"));

				empList.add(emp);
			}
		} catch(Exception e){
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
		
		return empList;
	}

	public ArrayList<Employee> selectDeptId(String deptId) {
		ArrayList<Employee> empList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from employee where dept_id = '"
		+ deptId +"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "studnet");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				Employee emp = new Employee();
				emp.setEmpId("emp_id");
				emp.setEmpName(rset.getString("emp_name"));
				emp.setEmpNo(rset.getString("emp_no"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setJobId(rset.getString("job_id"));
				emp.setSalary(rset.getInt("salary"));
				emp.setBonusPct(rset.getDouble("bonus_pct"));
				emp.setMarriage(rset.getString("marriage"));
				emp.setMgrId(rset.getString("mgr_id"));
				emp.setDeptId(deptId);

				empList.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}

	public int insert(Employee emp) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "insert into employee "
				+ "values (empid_seq.nextval, '"
				+ emp.getEmpName() + "', '"
				+ emp.getEmpNo() + "', '"
				+ emp.getEmail() + "', '"
				+ emp.getPhone() + "', '"
				+ emp.getHireDate() + "', '"
				+ emp.getJobId() + "', "
				+ emp.getSalary() + ", "
				+ emp.getBonusPct() + ", '"
				+ emp.getMarriage() + "', '"
				+ emp.getMgrId() +  "','"
				+ emp.getDeptId() + "')";
		try {
			//1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			conn.setAutoCommit(false); //DB에서 자동커밋 못하게 막음
			//3.
			stmt = conn.createStatement();
			//4.
			result = stmt.executeUpdate(query);
			//5.
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updatePhone(Employee emp) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "update employee set phone ='"
				+ emp.getPhone()+"' where emp_id = '"
				+ emp.getEmpId()+"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
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
		
		
		return result;
	}

	public int updateBonusPct(Employee emp) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "update employee set bonus_pct = "
				+ emp.getBonusPct()+" where emp_id = '"
				+ emp.getEmpId()+"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
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
		return result;
	}

	public int deleteEmployee(String empId) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "delete from employee where emp_id = '"
				+ empId +"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
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
		return result;
	}
	
}
