package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;  // 固定版本號
	private Integer id;
	private String employee_no;
	private String name;
	private String username;
	private String password;
	private String role;	//選擇管理員或是一般員工
	private Timestamp arrived_date;
	private Timestamp leaved_date;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String employee_no, String name, String username, String password, String role) {
		super();
		this.employee_no = employee_no;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	
	public Employee(String name, String username, String password, String role, Timestamp arrived_date) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.arrived_date = arrived_date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getArrived_date() {
		return arrived_date;
	}
	public void setArrived_date(Timestamp arrived_date) {
		this.arrived_date = arrived_date;
	}
	public Timestamp getLeaved_date() {
		return leaved_date;
	}
	public void setLeaved_date(Timestamp leaved_date) {
		this.leaved_date = leaved_date;
	}
	
	

	
}
