package controller.porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Member;
import model.Porder_Detail;
import service.impl.PorderServiceImpl;
import util.Session;
import util.Tool;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfirmUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmUI frame = new ConfirmUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	PorderServiceImpl psi = new PorderServiceImpl();

	/**
	 * Create the frame.
	 */
	public ConfirmUI() {
		Member sessionMember = Session.getMember();
		Employee sessionEmployee = Session.getEmployee();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("確認訂單");
		setSize(743, 482);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 707, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("確認訂單");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		lblNewLabel.setBounds(24, 0, 104, 52);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 707, 313);
		contentPane.add(scrollPane);
		
		JTextPane output = new JTextPane();
		scrollPane.setViewportView(output);
		output.setEditable(false);
		output.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		
		
		
		
		@SuppressWarnings("unchecked")
		
		List<Porder_Detail> porderdetail = (List<Porder_Detail>)Tool.readFile("porderdetail.txt");
		//ProductServiceImpl psi=new ProductServiceImpl();
		
		String show = "";
		if(Session.isEmployeeLogin())
		{
			show+="點餐人員: "+sessionEmployee.getEmployee_no()+"\n";
		}
		
		if (Session.isMemberLogin())
		{
			show=show+"訂購VIP會員: "+sessionMember.getUsername()+" "+sessionMember.getName()+"\n";
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
		
		output.setText(show);
				
		
		
		//******************Button*****************
		
		JButton checkbtn = new JButton("送出訂單");
		checkbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				psi.createOrder(porderdetail);
				PosUI.cart.clear();  //清空購物車
				Finished finished = new Finished();
				finished.setVisible(true);
				dispose();
			}
		});
		checkbtn.setBounds(572, 395, 145, 38);
		contentPane.add(checkbtn);
		
		JButton checkbtn_1 = new JButton("回上一頁");
		checkbtn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PosUI posui = new PosUI();
				posui.setVisible(true);
				dispose();
			}
		});
		checkbtn_1.setBounds(10, 395, 145, 38);
		contentPane.add(checkbtn_1);
		
		
	
		

	}
}
