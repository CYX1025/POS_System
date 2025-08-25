package controller.porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.Member;
import model.Porder_Detail;
import service.impl.MemberServiceImpl;
import service.impl.PorderServiceImpl;
import util.CreateExecl;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class Report extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton memberbtn = new JButton("會員報表");
		memberbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CreateExecl ce = new CreateExecl();
				File file = new File("member.xls");
				List<Member> list = new MemberServiceImpl().selectAllMember();
				String[] titlename2 = new String[]{"name","username","password","email","phone","address"};
				if(!file.exists())
				{
					ce.createExecl("member.xls", "會員報表", titlename2);
					ce.insertMember("member.xls", "會員報表", list);
					JOptionPane.showMessageDialog(Report.this, "已成功生成報表");
				}
				else
				{
					file.delete(); // 先刪除舊檔
					ce.createExecl("member.xls", "會員報表", titlename2);
					ce.insertMember("member.xls", "會員報表", list);
					JOptionPane.showMessageDialog(Report.this, "已成功生成報表");
				}
						
			}
		});
		memberbtn.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		memberbtn.setBounds(83, 78, 202, 73);
		contentPane.add(memberbtn);
		
		JButton Porder_detailbtn = new JButton("訂單明細報表");
		Porder_detailbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CreateExecl ce = new CreateExecl();
				File file = new File("porderDetail.xls");
				List<Porder_Detail> list = new PorderServiceImpl().findAllPorderDetail();
				String[] titlename1 = new String[]{"order_no","product_id","product_name","price","quantity","consumer"};
				if(!file.exists())
				{
					ce.createExecl("porderDetail.xls", "會員訂購明細報表", titlename1);
					ce.insertPorderDetail("porderDetail.xls", "會員訂購明細報表", list);
					JOptionPane.showMessageDialog(Report.this, "已成功生成報表");
				}
				else 
				{
					file.delete(); // 先刪除舊檔
					ce.createExecl("porderDetail.xls", "會員訂購明細報表", titlename1);
					ce.insertPorderDetail("porderDetail.xls", "會員訂購明細報表", list);
					JOptionPane.showMessageDialog(Report.this, "已成功生成報表");
				}
				

					
				
				
			}
		});
		Porder_detailbtn.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		Porder_detailbtn.setBounds(83, 161, 202, 73);
		contentPane.add(Porder_detailbtn);
		
		JLabel lblNewLabel = new JLabel("報表");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		lblNewLabel.setBounds(156, 10, 54, 47);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manager po = new Manager();
				po.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_2.setBounds(10, 275, 125, 47);
		contentPane.add(btnNewButton_2);

	}
}
