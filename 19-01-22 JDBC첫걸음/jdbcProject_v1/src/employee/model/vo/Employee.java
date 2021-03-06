package employee.model.vo;

import java.sql.Date;

//vo (value object : 값 저장용 객체)
//== do (domain object) == dto (data transfer object)
//== entity == bean
//데이터베이스 테이블의 한 행의 정보를 저장할 객체를 말함

//1. 필드는 모두 private 이어야 함
//2. 기본생성자와 매개변수 있는 생성자가 있어야 함
//3. 모든 필드에 대한 setter, getter 가 있어야 함
//4. 직렬화 처리해야 함
public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 1000L;
	
	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private Date hireDate;  //java.sql.Date를 써야함 DB와 연동할때
	private String jobId;
	private int salary;
	private double bonusPct;
	private String marriage;
	private String mgrId;
	private String detpId;
	
	public Employee() {}

	public Employee(String empId, String empName, String empNo, String email, String phone, Date hireDate, String jobId,
			int salary, double bonusPct, String marriage, String mgrId, String detpId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.bonusPct = bonusPct;
		this.marriage = marriage;
		this.mgrId = mgrId;
		this.detpId = detpId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonusPct() {
		return bonusPct;
	}

	public void setBonusPct(double bonusPct) {
		this.bonusPct = bonusPct;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getDetpId() {
		return detpId;
	}

	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return empId + ", " + empName + ", " + empNo + ", " + email + ", " + phone + ", " + hireDate + ", " + jobId
				+ ", " + salary + ", " + bonusPct + ", " + marriage + ", " + mgrId + ", " + detpId;
	}
	
/*	@Override
	public String toString() {
		return this.empId + ", " + this.empName + ", " + this.empNo + ", " + this.email + ", " +
				this.phone + ", " + this.hireDate + ", " + this.jobId + ", " + this.salary + ", " + 
				this.bonusPct + ", " + this.marriage + ", " + this.mgrId + ", " + this.detpId;
	}*/
	
	
}
