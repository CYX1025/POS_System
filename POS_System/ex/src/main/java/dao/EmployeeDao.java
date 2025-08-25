package dao;

import java.util.List;

import model.Employee;


public interface EmployeeDao {
	
	
	//create
	void addEmployee(Employee employee);
	
	//read
	Employee selectUsernamePassword(String username,String password); //登入用 
	Employee selectUsername(String username);
	Employee selectById(int id);
	Employee selectByEmployee_No(String employee_no);
	List<Employee>selectAll();
	List<Employee> keyword(String keyword);
	
	//update
	void update(Employee employee);
	void updateLeaveTime(Employee employee);
	
	//delete
	void delete(Employee employee);
	

}
