package emp;

public class Employee {

	int empNo;
	String name, position, dept;
	
	public Employee() {}
	
	public Employee(int empNo, String name, String position, String dept) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.position = position;
		this.dept = dept;
	}
	
	
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", name=" + name + ", position=" + position + ", dept=" + dept + "]";
	}
	
	
	
	
	
}
