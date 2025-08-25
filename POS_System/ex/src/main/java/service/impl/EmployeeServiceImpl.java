package service.impl;

import java.util.List;

import dao.impl.EmployeeDaoImpl;
import model.Employee;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	public static void main(String[] args) {
		
		/*
		List<Employee> list = new EmployeeServiceImpl().findAllEmployee();
		for(Employee x:list)
		{
			System.out.println(x.getEmployee_no()+"\t"+x.getUsername());
		}
		*/
		/*
		Employee em = new EmployeeDaoImpl().selectByEmployee_No("E0006");
		em.setPassword("666");
		new EmployeeServiceImpl().update(em);
*/
	}
	
	private static EmployeeDaoImpl edi = new EmployeeDaoImpl();

	@Override
	public boolean addEmployee(Employee employee) {
		boolean employeeisuse = false;
		Employee em = edi.selectUsername(employee.getUsername());
		if(em!=null)
		{
			System.out.print("已有此帳號");
		}
		else
		{
			edi.addEmployee(employee);
			employeeisuse = true;
		}
		
		return employeeisuse;
	}

	@Override
	public Employee login(String username, String password) {

		return edi.selectUsernamePassword(username, password);
	}

	@Override
	public Employee checkusername(String username) {
		return edi.selectUsername(username);
	}
	
	@Override
	public List<Employee> findAllEmployee() {
		return edi.selectAll();
	}
	
	@Override
	public Employee checkEmployeeNo(String employee_no) {
		return edi.selectByEmployee_No(employee_no);
	}
	
	
	@Override
	public List<Employee> keyword(String keyword) {
		return edi.keyword(keyword);
	}





	@Override
	public boolean update(Employee employee) {
		boolean update = false;
		Employee em = edi.selectByEmployee_No(employee.getEmployee_no());
		if(em!=null)
		{
			edi.update(employee);
			update = true;
		}
		return update;
	}
	
	@Override
	public boolean updateLeaveTime(Employee employee) {
		boolean update = false;
		Employee em = edi.selectByEmployee_No(employee.getEmployee_no());
		if(em!=null)
		{
			edi.updateLeaveTime(employee);
			update = true;
		}
		return update;
	}

	

	@Override
	public boolean delete(Employee employee) {
		boolean delete = false;
		Employee em = edi.selectByEmployee_No(employee.getEmployee_no());
		if(em!=null)
		{
			edi.delete(employee);
			delete = true;
		}
		return delete;
	}

	


}
