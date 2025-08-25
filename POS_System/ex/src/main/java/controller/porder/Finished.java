package controller.porder;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Member;
import model.Porder_Detail;
import util.Session;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.List;
import javax.swing.JScrollPane;

public class Finished extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finished frame = new Finished();
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
	public Finished() {
		
		
		
		setTitle("訂單完成");
		setSize(743, 482);
		setLocationRelativeTo(null);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 736, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("訂單成立!");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		lblNewLabel.setBounds(41, 0, 134, 52);
		panel.add(lblNewLabel);
		

		

		
		Member sessionMember = Session.getMember();
		Employee sessionEmployee = Session.getEmployee();
		
		@SuppressWarnings("unchecked")
		List<Porder_Detail> porderdetail = (List<Porder_Detail>)Tool.readFile("porderdetail.txt");
		
		String show = "";
		
		if(Session.isEmployeeLogin())
		{
			show+="點餐人員: "+sessionEmployee.getEmployee_no()+"\n";
		}
		if (Session.isMemberLogin())
		{
			show += "======================"+
					"\n客戶資料: "+
					"\n會員編號: "+sessionMember.getMember_no()+
					"\n客戶名: "+sessionMember.getUsername()+" "+sessionMember.getName()+
					"\n電話: "+sessionMember.getPhone()+
					"\n地址: "+sessionMember.getAddress()+
					"\n======================";
		}
		
		 
		
		int allTotal = 0;
		
		for(Porder_Detail list : porderdetail)
		{
			 
			allTotal+=list.getQuantity()*list.getPrice();
			
			 show=show+ "\n商品名稱: "+list.getProduct_name()+
					 	"\n商品價格: "+list.getPrice()+
					 	"\n商品數量: "+list.getQuantity()+
					 	"\n共: " +list.getQuantity()*list.getPrice()+"元"+"\n";
		}
		show = show+"\n全部共多少"+allTotal+"元";
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 736, 353);
		contentPane.add(scrollPane);
		
		
		JTextPane showorder = new JTextPane();
		showorder.setEditable(false);
		scrollPane.setViewportView(showorder);
		showorder.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		showorder.setText(show);
		
		
		//****************Button********************
		
		JButton btnNewButton = new JButton("重新下訂");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(Session.isEmployeeLogin())
				{
					Session.Memberisnull();
					new PosUI().setVisible(true);
					dispose();
				}
				else if (Session.isMemberLogin())
				{
					new PosUI().setVisible(true);
					
	    			dispose();
				}
				
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		btnNewButton.setBounds(10, 435, 158, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("列印");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					showorder.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_1.setBounds(283, 435, 185, 43);
		contentPane.add(btnNewButton_1);
		
		
		
	}
}
