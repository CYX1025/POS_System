package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDao;
import model.Employee;
import util.DbConnection;
import util.auto_numbering;

public class EmployeeDaoImpl implements EmployeeDao{

	public static void main(String[] args) {
		
		System.out.println(new EmployeeDaoImpl().selectAll());  
		//System.out.println(new EmployeeDaoImpl().selectByEmployee_No("E0001"));
		/*//addEmployee(Employee employee)
		Employee em = new Employee("E002", "chang", "Dennis", "123", "admin");
		new EmployeeDaoImpl().addEmployee(em);*/
		
		//System.out.println(new EmployeeDaoImpl().selectUsernamePassword("Dennis", "123"));

		System.out.println(new EmployeeDaoImpl().selectAll());
	}
	
	private static Connection conn = DbConnection.getDb();

	@Override
	public void addEmployee(Employee employee) {
		String sql = "insert into employee(employee_no, name, username, password, role, arrived_date) values(?,?,?,?,?,?)";
		try {
			String employeeNo = auto_numbering.EmployeeNo();
			employee.setEmployee_no(employeeNo);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getEmployee_no());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getUsername());
			ps.setString(4, employee.getPassword());
			ps.setString(5, employee.getRole());
			ps.setTimestamp(6, employee.getArrived_date());
			ps.executeUpdate();
			System.out.println("員工新增成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Employee selectUsernamePassword(String username, String password) {
		String sql = "select * from employee where username = ? and password = ?";
		Employee employee = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				employee.setLeaved_date(rs.getTimestamp("leaved_date"));
				System.out.println("員工帳號密碼正確");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("員工帳號密碼錯誤");
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Employee selectUsername(String username) {
		String sql = "select * from employee where username = ?";
		Employee employee = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				System.out.println("查詢到此帳號");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查詢不到此帳號");
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public Employee selectById(int id) {
		String sql = "select * from employee where  id= ?";
		Employee employee = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				System.out.println("此ID已存在");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("此ID不存在");
			e.printStackTrace();
		}	
		return employee;
	}
	
	@Override
	public Employee selectByEmployee_No(String employee_no) {
		String sql = "select * from employee where employee_no = ?";
		Employee employee =null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				employee.setLeaved_date(rs.getTimestamp("leaved_date"));
				System.out.println("此ID已存在");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("此ID不存在");
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> selectAll() {
		String sql = "select * from employee";
		List<Employee> list = new ArrayList<>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				employee.setLeaved_date(rs.getTimestamp("leaved_date"));
				list.add(employee);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	
	@Override
	public List<Employee> keyword(String keyword) {
		List<Employee> list = new ArrayList<>();
		String sql = "select * from employee where employee_no like ? or name like ? or username like ? "
					+ "or password like ? or role like ? or arrived_date like ? or leaved_date like ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			String searchKeyword = "%"+keyword+"%";
			ps.setString(1, searchKeyword);
			ps.setString(2, searchKeyword);
			ps.setString(3, searchKeyword);
			ps.setString(4, searchKeyword);
			ps.setString(5, searchKeyword);
			ps.setString(6, searchKeyword);
			ps.setString(7, searchKeyword);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployee_no(rs.getString("employee_no"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setArrived_date(rs.getTimestamp("arrived_date"));
				employee.setLeaved_date(rs.getTimestamp("leaved_date"));
				list.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	
	
	
	@Override
	public void update(Employee employee) {
		String sql = "update employee set name = ?, username = ?, password = ?, role = ? where employee_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getRole());
			ps.setString(5, employee.getEmployee_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void updateLeaveTime(Employee employee) {
		String sql = "update employee set leaved_date = ? where employee_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(1,now);
			ps.setString(2, employee.getEmployee_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	public void delete(Employee employee) {
		String sql = "delete from employee where employee_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getEmployee_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
