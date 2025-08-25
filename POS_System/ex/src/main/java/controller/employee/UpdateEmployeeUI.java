package controller.employee;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.porder.Manager;
import model.Employee;
import service.impl.EmployeeServiceImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UpdateEmployeeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTable employeeTable;

	private static EmployeeServiceImpl esi = new EmployeeServiceImpl();
	
	/**
	 * Create the frame.
	 */
	public UpdateEmployeeUI(String getEmployeeno) {
		
		setTitle("更新員工資料");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1092, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Employee em = esi.checkEmployeeNo(getEmployeeno);

		// JTable 欄位名稱
		String[] columnNames = {"ID", "員工編號", "姓名", "使用者名稱", "密碼", "角色", "入職時間", "離職時間"};
		// 單筆資料
		Object[][] rowData = {
			{ em.getId(), em.getEmployee_no(), em.getName(), em.getUsername(),
			  em.getPassword(), em.getRole(), em.getArrived_date(), em.getLeaved_date() }
		};

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1055, 269);
		contentPane.add(scrollPane);

		employeeTable = new JTable(model);
		employeeTable.setDefaultEditor(Object.class, null); // 禁用編輯
		employeeTable.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		employeeTable.setRowHeight(30);
		scrollPane.setViewportView(employeeTable);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("姓名:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(60, 353, 45, 43);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(115, 354, 349, 42);
		contentPane.add(name);
		
		JLabel lblUsername = new JLabel("使用者名稱:");
		lblUsername.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblUsername.setBounds(504, 353, 105, 43);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(619, 354, 351, 42);
		contentPane.add(username);
		
		JLabel lblPassword = new JLabel("密碼:");
		lblPassword.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblPassword.setBounds(60, 422, 45, 43);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(115, 423, 349, 42);
		contentPane.add(password);
		
		JLabel lblNewLabel_1 = new JLabel("請輸入想更改的資訊!");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		lblNewLabel_1.setBounds(20, 289, 269, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("角色:");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_2.setBounds(565, 422, 45, 43);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<>(new String[] {"Employee", "admin" });
	    comboBox.setFont(new Font("微軟正黑體", Font.BOLD, 20));
	    comboBox.setBounds(619, 419, 351, 43);
	    comboBox.setSelectedIndex(-1);  // 預設空白，回傳null
	    contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("確認更改");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Employee em = esi.checkEmployeeNo(getEmployeeno);
				String NAME = name.getText().trim();
				String USERNAME = username.getText().trim();
				String PASSWORD = password.getText().trim();
				Object selectedRole = comboBox.getSelectedItem(); //先存，再判斷 null
				String role = (selectedRole == null) ? null : selectedRole.toString();
				
				if (!NAME.isEmpty()) em.setName(NAME);
			    if (!USERNAME.isEmpty()) em.setUsername(USERNAME);
			    if (!PASSWORD.isEmpty()) em.setPassword(PASSWORD);
			    em.setRole(role);
			    
			    if(NAME.isEmpty() && USERNAME.isEmpty() && PASSWORD.isEmpty() && role ==null)
			    {
			    	JOptionPane.showMessageDialog(UpdateEmployeeUI.this,"無輸入任何資料!");
			    }
			    else
			    {
			    	esi.update(em);
			    	
			    	List<Employee> list = esi.findAllEmployee();
			    	Manager.employeeModel.setRowCount(0);// 先清空表格
			    	for(Employee emp : list)
			    	{
			    		Manager.employeeModel.addRow(new Object[]{emp.getId(), emp.getEmployee_no(), emp.getName(), emp.getUsername(),
			    				emp.getPassword(), emp.getRole(), emp.getArrived_date(), emp.getLeaved_date()});
			    	}
			    	
			    	
			    	JOptionPane.showMessageDialog(UpdateEmployeeUI.this,"修改完成!");
					dispose();
			    }
				
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton.setBounds(907, 504, 158, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("關閉");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton_1.setBounds(10, 504, 147, 54);
		contentPane.add(btnNewButton_1);
		

	}
}
