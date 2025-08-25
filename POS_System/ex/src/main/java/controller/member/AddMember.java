package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import controller.porder.PosUI;
import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField address;
	private JTextField email;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static MemberServiceImpl msi = new MemberServiceImpl();
	
	
	public AddMember() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609, 631);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(231, 231, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register as Member");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(112, 10, 348, 50);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setFont(new Font("新細明體", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(235, 93, 252, 50);
		contentPane.add(name);
		
		username = new JTextField();
		username.setFont(new Font("新細明體", Font.PLAIN, 20));
		username.setColumns(10);
		username.setBounds(235, 153, 252, 50);
		contentPane.add(username);
		
		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 20));
		password.setColumns(10);
		password.setBounds(234, 213, 252, 50);
		contentPane.add(password);
		
		phone = new JTextField();
		phone.setFont(new Font("新細明體", Font.PLAIN, 20));
		phone.setColumns(10);
		phone.setBounds(235, 333, 252, 50);
		contentPane.add(phone);
		
		address = new JTextField();
		address.setFont(new Font("新細明體", Font.PLAIN, 20));
		address.setColumns(10);
		address.setBounds(235, 393, 252, 50);
		contentPane.add(address);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblAddress.setBounds(130, 393, 95, 50);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number:");
		lblPhoneNumber_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPhoneNumber_1.setBounds(55, 333, 170, 50);
		contentPane.add(lblPhoneNumber_1);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPassword.setBounds(112, 213, 112, 50);
		contentPane.add(lblPassword);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblUserName.setBounds(98, 153, 127, 50);
		contentPane.add(lblUserName);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(155, 93, 70, 50);
		contentPane.add(lblNewLabel_1);
		
		email = new JTextField();
		email.setFont(new Font("新細明體", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(235, 273, 252, 50);
		contentPane.add(email);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblEmail.setBounds(150, 273, 75, 50);
		contentPane.add(lblEmail);
	
		//********************Button******************
		JButton signUpbtn = new JButton("Sign Up");
		signUpbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String NAME = name.getText().trim();
				String USERNAME = username.getText().trim();
				String PASSWORD = password.getText().trim();
				String EMAIL = email.getText().trim();
				String PHONE = phone.getText().trim();
				String ADDRESS = address.getText().trim();
						
				if(NAME.equals("") || USERNAME.equals("") || PASSWORD.equals("") || EMAIL.equals("") || PHONE.equals("") ||ADDRESS.equals(""))  
				{
					JOptionPane.showMessageDialog(AddMember.this,"請填入完整資料!");
				}
				else if(msi.checkusername(USERNAME)!=null)
				{
					JOptionPane.showMessageDialog(AddMember.this,"帳號已存在");
				}
				else
				{
					Member m = new Member(NAME, USERNAME, PASSWORD, EMAIL, PHONE, ADDRESS);
					msi.addMember(m);	
					JOptionPane.showMessageDialog(AddMember.this, "新增成功！");
					Tool.saveFile(m, "member.txt");
					PosUI posui = new PosUI();
					posui.setVisible(true);
					dispose();
					
					
				}
	
			}
		});
		signUpbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		signUpbtn.setBounds(205, 467, 170, 50);
		contentPane.add(signUpbtn);
		
		

	}
}
