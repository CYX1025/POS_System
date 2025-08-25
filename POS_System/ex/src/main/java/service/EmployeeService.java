package service;

import java.util.List;

import model.Employee;



public interface EmployeeService {
	
	//create
	boolean addEmployee(Employee employee); //新增member+判斷
	
	//read
	Employee login(String username,String password);//登入
	Employee checkusername(String username);
	List<Employee>findAllEmployee();
	Employee checkEmployeeNo(String employee_no);
	List<Employee> keyword(String keyword);
	
	//update
	boolean update(Employee employee);
	boolean updateLeaveTime(Employee employee);
	
	//delete
	boolean delete(Employee employee);

}
