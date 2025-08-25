package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Member;
import service.impl.MemberServiceImpl;
import util.Session;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class selectMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchMember;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectMemberUI frame = new selectMemberUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static MemberServiceImpl msi = new MemberServiceImpl();

	/**
	 * Create the frame.
	 */
	public selectMemberUI() {
		
		setTitle("選擇會員");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 246, 246));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchMember = new JTextField();
		searchMember.setFont(new Font("新細明體", Font.PLAIN, 14));
		searchMember.setColumns(10);
		searchMember.setBounds(10, 77, 269, 42);
		contentPane.add(searchMember);
		
		JLabel lblNewLabel = new JLabel("點選會員", JLabel.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 10, 1070, 42);
		contentPane.add(lblNewLabel);
		
		
		List<Member> listMember = msi.selectAllMember();
        String[] memberColumns = {"ID","會員編號","姓名","帳號","密碼","信箱","電話","地址"};
        DefaultTableModel memberModel = new DefaultTableModel(memberColumns, 0);
        
        JTable memberTable = new JTable(memberModel);
        memberTable.setDefaultEditor(Object.class, null); // 禁用編輯
        memberTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        memberTable.setRowHeight(25);
        JScrollPane scrollPane_1 = new JScrollPane(memberTable);
        scrollPane_1.setBounds(10, 129, 1070, 269);
        contentPane.add(scrollPane_1);
        
        
     // 初始化資料
        for(Member m : listMember){
            memberModel.addRow(new Object[]{m.getId(), m.getMember_no(), m.getName(), m.getUsername(),
                                           m.getPassword(), m.getEmail(), m.getPhone(), m.getAddress()});
        }
		
		
		
		JButton searchMemberbtn = new JButton("搜尋");
		searchMemberbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String keyword = searchMember.getText().trim();
        		List<Member> list = msi.keyword(keyword);
        		memberModel.setRowCount(0);// 先清空表格
        		for(Member m: list)
        		{
        			memberModel.addRow(new Object[]{m.getId(), m.getMember_no(), m.getName(), m.getUsername(),
                                           m.getPassword(), m.getEmail(), m.getPhone(), m.getAddress()});
        		}
			}
		});
		searchMemberbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		searchMemberbtn.setBounds(292, 77, 96, 42);
		contentPane.add(searchMemberbtn);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		btnNewButton.setBounds(10, 424, 131, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("確定");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = memberTable.getSelectedRow();
				if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(selectMemberUI.this, "請先選擇一筆訂單！");
		            return;
		        }
				String MemberNO = memberModel.getValueAt(selectedRow, 1).toString();
				Member m = msi.checkMemberNo(MemberNO);//取得資料
				Session.insertMemberData(m);
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton_1.setBounds(949, 424, 131, 53);
		contentPane.add(btnNewButton_1);
		
		
		
		

	}
}
