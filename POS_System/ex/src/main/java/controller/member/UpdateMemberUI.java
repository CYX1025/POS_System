package controller.member;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.porder.Manager;
import model.Member;
import service.impl.MemberServiceImpl;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField phone;
	private JTextField email;
	private JTextField address;

	private static MemberServiceImpl msi = new MemberServiceImpl();
	private DefaultTableModel tableModel;
	
	/**
	 * Create the frame.
	 */
	public UpdateMemberUI(String getMemberno) {
		
		setTitle("更新會員資料");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1092, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		Member m = msi.checkMemberNo(getMemberno);
		
		// JTable
		String[] columnNames = {"ID", "會員編號", "姓名", "使用者名稱", "密碼", "信箱", "行動電話", "地址"};
		Object[][] data = {
			{m.getId(), m.getMember_no(), m.getName(), m.getUsername(),
			 m.getPassword(), m.getEmail(), m.getPhone(), m.getAddress()}
		};
		
		tableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(tableModel);
		table.setDefaultEditor(Object.class, null); // 禁用編輯
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 1056, 137);
		contentPane.add(scrollPane);
		
		
		JLabel lblNewLabel = new JLabel("姓名:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(61, 221, 45, 43);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(116, 222, 349, 42);
		contentPane.add(name);
		
		JLabel lblUsername = new JLabel("使用者名稱:");
		lblUsername.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblUsername.setBounds(505, 221, 105, 43);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(620, 222, 351, 42);
		contentPane.add(username);
		
		JLabel lblPassword = new JLabel("密碼:");
		lblPassword.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblPassword.setBounds(61, 290, 45, 43);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(116, 291, 349, 42);
		contentPane.add(password);
		
		JLabel lblNewLabel_1 = new JLabel("請輸入想更改的資訊!");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		lblNewLabel_1.setBounds(21, 157, 269, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("行動電話:");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_2.setBounds(526, 290, 85, 43);
		contentPane.add(lblNewLabel_2);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(621, 291, 350, 42);
		contentPane.add(phone);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(116, 360, 349, 42);
		contentPane.add(email);
		
		JLabel lblEmail = new JLabel("電子信箱:");
		lblEmail.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblEmail.setBounds(21, 359, 85, 43);
		contentPane.add(lblEmail);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(622, 360, 349, 42);
		contentPane.add(address);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblAddress.setBounds(530, 359, 82, 43);
		contentPane.add(lblAddress);
		
		JButton btnNewButton = new JButton("確認更改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Member m = msi.checkMemberNo(getMemberno);
				
				String NAME = name.getText().trim();
				String USERNAME = username.getText().trim();
				String PASSWORD = password.getText().trim();
				String EMAIL = email.getText().trim();
				String PHONE = phone.getText().trim();
				String ADDRESS = address.getText().trim();
				
				if (!NAME.isEmpty()) m.setName(NAME);
			    if (!USERNAME.isEmpty()) m.setUsername(USERNAME);
			    if (!PASSWORD.isEmpty()) m.setPassword(PASSWORD);
			    if (!EMAIL.isEmpty()) m.setEmail(EMAIL);
			    if (!PHONE.isEmpty()) m.setPhone(PHONE);
			    if (!ADDRESS.isEmpty()) m.setAddress(ADDRESS);
				 
				if(NAME.isEmpty() && USERNAME.isEmpty() && PASSWORD.isEmpty() && EMAIL.isEmpty() && PHONE.isEmpty() && ADDRESS.isEmpty())
				{
					JOptionPane.showMessageDialog(UpdateMemberUI.this,"無輸入任何資料!");
				}
				else
				{
					msi.updateMember(m);

					List<Member> listMember = msi.selectAllMember();
					Manager.memberModel.setRowCount(0);// 先清空表格
					for(Member mem : listMember)
					{
						Manager.memberModel.addRow(new Object[]{mem.getId(), mem.getMember_no(), mem.getName(), mem.getUsername(),
	                                           m.getPassword(), mem.getEmail(), mem.getPhone(), mem.getAddress()});
	        		}
					JOptionPane.showMessageDialog(UpdateMemberUI.this,"修改完成!");
					dispose();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton.setBounds(907, 457, 158, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("關閉");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton_1.setBounds(10, 457, 147, 54);
		contentPane.add(btnNewButton_1);
		

	}
}
