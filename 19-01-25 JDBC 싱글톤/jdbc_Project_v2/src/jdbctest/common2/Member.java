package jdbctest.common2;

import java.io.Serializable;

public class Member implements Serializable{
	private static final long SerialVersionUID = 90930903L;
	
	private int mId;
	private String mName;
	private int salary;
	
	public Member() {}

	public Member(int mId, String mName, int salary) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.salary = salary;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	@Override
	public String toString() {
		return mId + "\t" + mName + "\t" + salary;
	}
	
}
