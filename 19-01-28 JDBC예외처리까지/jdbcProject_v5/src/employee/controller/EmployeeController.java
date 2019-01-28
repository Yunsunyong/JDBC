package employee.controller;

import java.util.ArrayList;
import java.util.HashMap;

import employee.exception.EmployeeException;
import employee.model.dao.EmployeeDao;
import employee.model.service.EmployeeService;
import employee.model.vo.Employee;
import employee.view.EmployeeMenu;

public class EmployeeController {
	//DI 선언
	private EmployeeService edservice = new EmployeeService();
	
	//기본생성자
	public EmployeeController() {}

	public HashMap<String,Employee> selectAll() {
		HashMap<String,Employee> empMap = null;
		
		try {
			empMap = edservice.selectMap();
			if(empMap.size() > 0) {
				System.out.println("전체조회 성공!");
			}
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
		
		
		return empMap;
	}

	public Employee selectEmployee(String empId) {
		Employee emp = null;
		try {
			emp = edservice.selectOne(empId);
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
		return emp;
	}

	public HashMap<String,Employee> selectJobId(String jobId) {
		HashMap<String, Employee> empMap = null;
		try {
			empMap = edservice.selectJobMap(jobId);
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
		return empMap;
	}

	public HashMap<String,Employee> selectDeptId(String DeptId) {
		HashMap<String, Employee> empMap = null;
		try {
			empMap = edservice.selectDeptId(DeptId);
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
		return empMap;
	}

	public void insertEmployee(Employee emp) {
		try {
			int result = edservice.insert(emp);
			if(result > 0) {
				System.out.println("\n직원 정보 등록 성공!");
			}
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
		return;
	}

	public void updatePhone(Employee emp) {
		try {
			int result = edservice.updatePhone(emp);
			if(result > 0) {
				System.out.println("\n직원의 전화번호 변경 성공!");
			}
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
	}

	public void updateBonusPct(Employee emp) {
		try {
			int result = edservice.updateBonusPct(emp);
			if(result > 0) {
				System.out.println("\n직원의 보너스포인트 변경 성공!!");
			}
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
	}

	public void deleteEmployee(String empId) {
		try {
			int result = edservice.deleteEmployee(empId);
			if(result > 0) {
				System.out.println("\n직원 정보 삭제 성공!!");
			}
		} catch (EmployeeException e) {
			new EmployeeMenu().printError(e.getMessage());
			new EmployeeMenu().displayMenu();
		}
	}
	
}
