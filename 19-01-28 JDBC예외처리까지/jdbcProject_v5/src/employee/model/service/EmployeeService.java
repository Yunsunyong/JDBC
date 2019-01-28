package employee.model.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import employee.exception.EmployeeException;
import employee.model.dao.EmployeeDao;
import employee.model.vo.Employee;
//import common.JDBCTemplate;
import static common.JDBCTemplate.*;
//Connection 관리와 트랜잭션 처리가 주목적임
public class EmployeeService {
	//DI
	private EmployeeDao edao = new EmployeeDao();
	
	public EmployeeService() {}

	public HashMap<String, Employee> selectMap() throws EmployeeException{
		Connection conn = getConnection();
		HashMap<String,Employee> empMap = edao.selectMap(conn);
		close(conn);
		return empMap;
	}

	public Employee selectOne(String empId) throws EmployeeException{
		Connection conn = getConnection();
		Employee emp = edao.selectOne(conn, empId);
		close(conn);
		return emp;
	}

	public HashMap<String,Employee> selectJobMap(String jobId) throws EmployeeException{
		Connection conn = getConnection();
		HashMap<String,Employee> empMap = edao.selectJobMap(conn, jobId);
		close(conn);
		return empMap;
	}

	public HashMap<String,Employee> selectDeptId(String deptId) throws EmployeeException{
		Connection conn = getConnection();
		HashMap<String,Employee> empMap = edao.selectDeptId(conn, deptId);
		close(conn);
		return empMap;
	}
	
	public int insert(Employee emp) throws EmployeeException{
		Connection conn = getConnection();
		int result = edao.insert(conn, emp);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public int updatePhone(Employee emp) throws EmployeeException{
		Connection conn = getConnection();
		int result = edao.updatePhone(conn, emp);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public int updateBonusPct(Employee emp) throws EmployeeException{
		Connection conn = getConnection();
		int result = edao.updateBonusPct(conn, emp);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public int deleteEmployee(String empId) throws EmployeeException{
		Connection conn = getConnection();
		int result = edao.deleteEmployee(conn, empId);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

}
