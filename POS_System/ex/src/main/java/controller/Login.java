package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.member.AddMember;
import controller.porder.Manager;
import controller.porder.PosUI;
import model.Employee;
import model.Member;
import service.impl.EmployeeServiceImpl;
import service.impl.MemberServiceImpl;
import util.Session;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;


public class Login extends JFrame {
	
	private JPanel contentPane;
	private JTextField username;
	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		private static MemberServiceImpl msi = new MemberServiceImpl();
		private static EmployeeServiceImpl esi = new EmployeeServiceImpl();
		
		
		public Login() {

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 722, 634);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    

	    ImageIcon icon = new ImageIcon(Login.class.getResource("/image/interface.jpg")); //相對位置
	    Image scaledImage = icon.getImage().getScaledInstance(722, 634, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);
	    contentPane.setLayout(null);
	    JLabel background = new JLabel(scaledIcon);
	    background.setBounds(0, 0, 706, 595);
	    background.setLayout(null); // 必須設定 layout 為 null，才能放入元件
	    contentPane.add(background); // 最後只加這個背景到 contentPane

	    
	    JLabel userIDLabel = new JLabel("User ID:");
	    userIDLabel.setForeground(Color.WHITE);
	    userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    userIDLabel.setBounds(160, 285, 84, 46);
	    background.add(userIDLabel);

	    JLabel userPasswordLabel = new JLabel("Password:");
	    userPasswordLabel.setForeground(Color.WHITE);
	    userPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    userPasswordLabel.setBounds(142, 341, 112, 53);
	    background.add(userPasswordLabel);

	    username = new JTextField();
	    username.setBounds(246, 293, 246, 35);
	    background.add(username);

	    JPasswordField password = new JPasswordField();
	    password.setBounds(245, 348, 246, 35);
	    background.add(password);

	    JLabel messageLabel = new JLabel();
	    messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    messageLabel.setBounds(250, 528, 395, 53);
	    background.add(messageLabel);

	    JComboBox<String> comboBox = new JComboBox<>(new String[] { "Member", "Employee" });
	    comboBox.setFont(new Font("微軟正黑體", Font.BOLD, 20));
	    comboBox.setBounds(300, 233, 126, 40);
	    background.add(comboBox);
	    
//***************************Button*******************************
	    
	    JButton loginButton = new JButton("Login");
	    loginButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		String USERNAME = username.getText().trim();
	    		String PASSWORD = new String(password.getPassword()).trim();
	    		String role = comboBox.getSelectedItem().toString();
	    		if (USERNAME.equals("") || PASSWORD.equals("")) {
	    			JOptionPane.showMessageDialog(Login.this, "請輸入帳號與密碼！");
	    			return;
	    		}
	    		
	    		if("Member".equals(role))
	    		{
	    			Member member = msi.login(USERNAME, PASSWORD);

	    			
	    			if (member != null) {
		    			 
		    			Tool.saveFile(member, "member.txt");	//用不到先保留
		    			Session.setMember(member);				//儲存登入者資料
		    			JOptionPane.showMessageDialog(Login.this, "登入成功！");
		    			PosUI posui = new PosUI();
			    		posui.setVisible(true);
			    		dispose();
			 
		    		} else {
		    			JOptionPane.showMessageDialog(Login.this, "帳號或密碼錯誤！");
		    		}
	    		}
	    		else if("Employee".equals(role))
	    		{
	    			Employee employee =esi.login(USERNAME, PASSWORD);
	    			
	    			if(employee!=null && (employee.getLeaved_date())!=null)
	    			{
	    				JOptionPane.showMessageDialog(Login.this, "此員工已離職！");
	    			}	
	    			else if(employee!=null && (employee.getLeaved_date())==null)
	    			{
	    				Tool.saveFile(employee, "employee.txt");	//用不到先保留
	    				Session.setEmployee(employee);				//儲存登入者資料
	    				JOptionPane.showMessageDialog(Login.this, "登入成功！");
	    				Manager pm = new Manager();
	    				pm.setVisible(true);
	    				dispose();
	    			}
	    			else {
		    			JOptionPane.showMessageDialog(Login.this, "帳號或密碼錯誤！");
		    		}	
	    		}

	    	}
	    });
	    loginButton.setBackground(Color.WHITE);
	    loginButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
	    loginButton.setBounds(279, 404, 156, 46);
	    background.add(loginButton);
	    getRootPane().setDefaultButton(loginButton);	//按Enter登入

	    JButton resetButton = new JButton("Reset");
	    resetButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		AddMember addmember = new AddMember();
	    		addmember.setVisible(true);
	    		}
	    });
	    resetButton.setBackground(Color.WHITE);
	    resetButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
	    resetButton.setBounds(281, 478, 154, 40);
	    background.add(resetButton);
	    

	 // 監聽 comboBox 切換
	    comboBox.addActionListener(e -> {
	        String role = comboBox.getSelectedItem().toString();
	        if ("Member".equals(role)) {
	            resetButton.setVisible(true);
	        } else {
	            resetButton.setVisible(false);
	        }
	    });
	    
	    
	    
	    
	    
	    

	    
	}
}
