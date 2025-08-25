package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import controller.porder.Manager;

import model.Employee;
import service.impl.EmployeeServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.List;

public class AddEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JComboBox<String> role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static EmployeeServiceImpl esi = new EmployeeServiceImpl();

	/**
	 * Create the frame.
	 */
	public AddEmployee() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609,485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(231, 231, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterAsEmployee = new JLabel("Register as Employee");
		lblRegisterAsEmployee.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblRegisterAsEmployee.setBounds(109, 10, 364, 50);
		contentPane.add(lblRegisterAsEmployee);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(141, 94, 70, 50);
		contentPane.add(lblNewLabel_1);
		
		JTextField name = new JTextField();
		name.setFont(new Font("新細明體", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(221, 94, 252, 50);
		contentPane.add(name);
		
		username = new JTextField();
		username.setFont(new Font("新細明體", Font.PLAIN, 20));
		username.setColumns(10);
		username.setBounds(221, 154, 252, 50);
		contentPane.add(username);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblUserName.setBounds(84, 154, 127, 50);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPassword.setBounds(98, 214, 112, 50);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 20));
		password.setColumns(10);
		password.setBounds(220, 214, 252, 50);
		contentPane.add(password);
		
		role = new JComboBox<>(new String[] {"Admin", "Employee"});
		role.setFont(new Font("新細明體", Font.PLAIN, 20));
		role.setBounds(221, 274, 252, 50);
		contentPane.add(role);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblRole.setBounds(155, 274, 56, 50);
		contentPane.add(lblRole);
		
		JLabel showMessage = new JLabel("");
		showMessage.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		showMessage.setBounds(231, 467, 224, 35);
		contentPane.add(showMessage);
		
		JButton signUpbtn = new JButton("Sign Up");
		signUpbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String NAME = name.getText().trim();
				String USERNAME = username.getText().trim();
				String PASSWORD = password.getText().trim();
				String ROLE = role.getSelectedItem().toString().trim();
				Timestamp now = new Timestamp(System.currentTimeMillis());
				
				if(NAME.equals("") || USERNAME.equals("") || PASSWORD.equals(""))
				{
					JOptionPane.showMessageDialog(AddEmployee.this,"請填入完整資料!");
					
				}
				else if(esi.checkusername(USERNAME)!=null)
				{
					JOptionPane.showMessageDialog(AddEmployee.this,"帳號已存在!");
				}
				
				else
				{
					Employee em = new Employee(NAME,USERNAME,PASSWORD,ROLE,now);
					esi.addEmployee(em);
									
					List<Employee> list = esi.findAllEmployee();
					Manager.employeeModel.setRowCount(0);// 先清空表格
					for(Employee emplo : list)
					{
						Manager.employeeModel.addRow(new Object[]{emplo.getId(), emplo.getEmployee_no(), emplo.getName(), emplo.getUsername(),
								emplo.getPassword(), emplo.getRole(), emplo.getArrived_date(), emplo.getLeaved_date()});
					}
	
					JOptionPane.showMessageDialog(AddEmployee.this,"新增成功!");
					Tool.saveFile(em, "employee.txt");
					dispose();
				}
			}
		});
		signUpbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		signUpbtn.setBounds(221, 343, 170, 50);
		contentPane.add(signUpbtn);
		
		JButton backbtn = new JButton("Close");
		backbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		backbtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		backbtn.setBounds(22, 401, 112, 37);
		contentPane.add(backbtn);
		

	}
}
