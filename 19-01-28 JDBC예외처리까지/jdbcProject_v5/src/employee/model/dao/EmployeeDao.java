package employee.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import employee.exception.EmployeeException;
import employee.model.vo.Employee;
import static common.JDBCTemplate.*;

public class EmployeeDao {
	//DI
	private Properties prop = new Properties();
	
	//기본생성자
	public EmployeeDao() {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//직원 전체 조회 처리용 메소드
	public HashMap<String, Employee> selectMap(Connection conn) throws EmployeeException{
		HashMap<String, Employee> empMap = new HashMap<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectall");
		
		try {
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
				
				empMap.put(emp.getEmpId(), emp);
			}
			
			if(empMap.size() == 0) {
				throw new EmployeeException("조회된 정보가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
		 	//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}
		return empMap;
	}

	public Employee selectOne(Connection conn, String empId) throws EmployeeException{
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectone");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				emp = new Employee();
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
			
			if(emp == null) {
				throw new EmployeeException(empId + " 사번에 대한 직원 정보가 존재하지 않습니다.");
			}
		} catch(SQLException e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return emp;
	}

	public HashMap<String,Employee> selectJobMap(Connection conn, String jobId) throws EmployeeException{
		HashMap<String,Employee> empMap = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectjob"); 
		
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, jobId);
				rset = pstmt.executeQuery();
			while (rset.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rset.getString("emp_id"));
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

				empMap.put(emp.getEmpId(),emp);
			}
			
			if(empMap.size() == 0) {
				throw new EmployeeException(jobId + " 직급에 대한 직원 정보가 존재하지 않습니다.");
			}
		} catch(Exception e){
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return empMap;
	}

	public HashMap<String,Employee> selectDeptId(Connection conn, String deptId) throws EmployeeException{
		HashMap<String,Employee> empMap = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectdept");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deptId);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rset.getString("emp_id"));
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

				empMap.put(emp.getEmpId(),emp);
			}
			
			if(empMap.size() == 0) {
				throw new EmployeeException(deptId + " 부서번호에 대한 직원 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return empMap;
	}

	public int insert(Connection conn, Employee emp) throws EmployeeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insert");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpNo());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getPhone());
			pstmt.setDate(5, emp.getHireDate());
			pstmt.setString(6, emp.getJobId());
			pstmt.setInt(7, emp.getSalary());
			pstmt.setDouble(8, emp.getBonusPct());
			pstmt.setString(9, emp.getMarriage());
			pstmt.setString(10, emp.getMgrId());
			pstmt.setString(11, emp.getDeptId());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new EmployeeException("새 직원 등록 실패!!!");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			rollback(conn);
			throw new EmployeeException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePhone(Connection conn, Employee emp) throws EmployeeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updatep");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getPhone());
			pstmt.setString(2, emp.getEmpId());
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new EmployeeException(emp.getEmpId() +" 사번인 직원의 전화번호 수정 실패!!!");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			rollback(conn);
			throw new EmployeeException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBonusPct(Connection conn, Employee emp) throws EmployeeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateb");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, emp.getBonusPct());
			pstmt.setString(2, emp.getEmpId());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new EmployeeException(emp.getEmpId() +" 사번인 직원의 보너스포인트 수정 실패!!!");
			}
		} catch (Exception e) {
			//printStackTrace();
			rollback(conn);
			throw new EmployeeException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteEmployee(Connection conn, String empId) throws EmployeeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			//e.printStackTrace();
			rollback(conn);
			throw new EmployeeException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
