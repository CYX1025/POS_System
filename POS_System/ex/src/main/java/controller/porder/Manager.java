package controller.porder;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.employee.AddEmployee;
import controller.employee.UpdateEmployeeUI;
import controller.member.UpdateMemberUI;
import model.Employee;
import model.Member;
import model.Porder;
import model.Porder_Detail;
import service.impl.EmployeeServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.PorderServiceImpl;
import util.CreateExecl;
import util.Session;
import util.Tool;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	
	Employee employee = (Employee) Tool.readFile("employee.txt");
	
	private static PorderServiceImpl psi = new PorderServiceImpl();
	private static EmployeeServiceImpl esi = new EmployeeServiceImpl();
	private static MemberServiceImpl msi = new MemberServiceImpl();
	private JTextField searchPorder;
	private JTextField searchMember;
	private JTextField searchEmployee;
	
	
	public static DefaultTableModel memberModel; 	//生成全域變數
	public static DefaultTableModel employeeModel;	//生成全域變數
	
	/**
     * 無參數建構子：預設顯示第一個頁籤
     */
		public Manager() {
			initUI();
    	}

    	/**
     	* 可指定預設頁籤的建構子
     	*/
    	public Manager(int tabIndex) {
    		initUI();
    		tabbedPane.setSelectedIndex(tabIndex); // ⭐ 指定顯示哪個頁籤
    	}

    	/**
    	* UI 初始化方法（共用）
     	*/
    	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 703);
		
		setTitle("總後台");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(null);
		contentPane.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(245, 245, 220));
		
		JLabel lblNewLabel = new JLabel("POS營運銷售系統管理", JLabel.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 30));
		titlePanel.add(lblNewLabel);
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 802, 470);
		tabbedPane.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
        
        
        
        


		
		// ===== 分頁 1: POS 作業系統 =====
        JPanel posPanel = new JPanel();
        posPanel.setBackground(new Color(255, 255, 255));
        
        RoundedButton Posin = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
        Posin.setText("進入POS系統");
        Posin.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        Posin.setBounds(295, 184, 481, 172);
        //contentPane.add(btnNewButton);
        posPanel.add(Posin,BorderLayout.CENTER);
        tabbedPane.addTab("POS作業系統", posPanel);
        posPanel.setLayout(null);
        Posin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		PosUI posui = new PosUI();
        		posui.setVisible(true);
    			dispose();
        	}
        });
        
        
        
        
        // ===== 分頁 2: 訂單管理 =====
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(new Color(240, 240, 240));
        tabbedPane.addTab("訂單管理", orderPanel);
        orderPanel.setLayout(null);
		
		
        List<Porder> listPorder = psi.findAllPorder();
        String[] orderColumns = {"訂單編號", "建立時間"};
        DefaultTableModel orderModel = new DefaultTableModel(orderColumns,0);
        
        JTable orderTable = new JTable(orderModel);
        orderTable.setDefaultEditor(Object.class, null); // 禁用編輯
        orderTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        orderTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(10, 62, 1055, 269);
        orderPanel.add(scrollPane);
        
        
     // 初始化資料
        for(Porder p : listPorder){
            orderModel.addRow(new Object[]{p.getOrder_no(), p.getCreate_time()});
        }
		
		searchPorder = new JTextField();
		searchPorder.setFont(new Font("新細明體", Font.PLAIN, 14));
		searchPorder.setColumns(10);
		searchPorder.setBounds(10, 10, 269, 42);
		orderPanel.add(searchPorder);
		
		
		//*******************Button*************************
		JButton searchPorderdetailbtn = new JButton("查看詳情");
		searchPorderdetailbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		searchPorderdetailbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = orderTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆訂單！");
		            return;
		        }
				
		        String orderNo = orderModel.getValueAt(selectedRow, 0).toString();
		        new ShowPorderDetail(orderNo).setVisible(true);
			}
		});
		searchPorderdetailbtn.setBounds(10, 347, 130, 42);
		orderPanel.add(searchPorderdetailbtn);
		
		
		JButton deletePorderbtn = new JButton("刪除訂單");
		deletePorderbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = orderTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆訂單！");
		            return;
		        }
		        
		        String orderNo = orderModel.getValueAt(selectedRow, 0).toString();
		        
		     // 跳出確認視窗
		        int result = JOptionPane.showConfirmDialog(
		                Manager.this,
		                "確定要刪除該筆訂單嗎？",
		                "刪除確認",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.WARNING_MESSAGE
		        );
		        
		        if (result == JOptionPane.YES_OPTION)
		        {
		        	psi.delete(orderNo);
		        	
		        	List<Porder> list = psi.findAllPorder();
		        	orderModel.setRowCount(0);// 先清空表格
					for(Porder p : list)
					{
						orderModel.addRow(new Object[]{p.getOrder_no(), p.getCreate_time()});
					}
		        	JOptionPane.showMessageDialog(Manager.this, "訂單已刪除！");
		        }
		        else {
	                JOptionPane.showMessageDialog(Manager.this, "刪除失敗，請再試一次。");
	            }
		        
		        
				
				
			}
		});
		deletePorderbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		deletePorderbtn.setBounds(935, 347, 130, 42);
		if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
			orderPanel.add(deletePorderbtn);
		}
		
		
		
		
		JButton searchPorderbtn = new JButton("搜尋");
		searchPorderbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String keyword = searchPorder.getText().trim();
				List<Porder> list = psi.keyword(keyword);
				
				orderModel.setRowCount(0);// 先清空表格
				for(Porder p : list)
				{
					orderModel.addRow(new Object[]{p.getOrder_no(), p.getCreate_time()});
				}

			}
		});
		searchPorderbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		searchPorderbtn.setBounds(292, 10, 96, 42);
		orderPanel.add(searchPorderbtn);
		
        

		
		
		// ===== 分頁 3: 會員管理 =====
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(null);
        tabbedPane.addTab("會員管理", memberPanel);
        
        List<Member> listMember = msi.selectAllMember();
        String[] memberColumns = {"ID","會員編號","姓名","帳號","密碼","信箱","電話","地址"};
        memberModel = new DefaultTableModel(memberColumns, 0);
        
 
        
        JTable memberTable = new JTable(memberModel);
        memberTable.setDefaultEditor(Object.class, null); // 禁用編輯
        memberTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        memberTable.setRowHeight(25);
        
        JScrollPane scrollPane_1 = new JScrollPane(memberTable);
        scrollPane_1.setBounds(10, 62, 1055, 269);
        memberPanel.add(scrollPane_1);
        
        
     // 初始化資料
        for(Member m : listMember){
            memberModel.addRow(new Object[]{m.getId(), m.getMember_no(), m.getName(), m.getUsername(),
                                           m.getPassword(), m.getEmail(), m.getPhone(), m.getAddress()});
        }
        
        
        
        searchMember = new JTextField();
        searchMember.setFont(new Font("新細明體", Font.PLAIN, 14));
        searchMember.setColumns(10);
        searchMember.setBounds(10, 10, 269, 42);
        memberPanel.add(searchMember);
        
        JButton searchMemberbtn = new JButton("搜尋");
        searchMemberbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        searchMemberbtn.setBounds(292, 10, 96, 42);
        memberPanel.add(searchMemberbtn);
        
        JButton updateMemberbtn = new JButton("修改資料");
        updateMemberbtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int selectedRow = memberTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆資料！");
		            return;
		        }
		        String MemberNo = memberTable.getValueAt(selectedRow, 1).toString();
		        new UpdateMemberUI(MemberNo).setVisible(true);
        		
        		
        	}
        });
        updateMemberbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        updateMemberbtn.setBounds(10, 347, 130, 42);
        if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
        	memberPanel.add(updateMemberbtn);
		}
        
		
        JButton deleteMemberbtn = new JButton("刪除會員");
        deleteMemberbtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int selectedRow = memberTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆資料！");
		            return;
		        }
		        
		        String MemberNo = memberTable.getValueAt(selectedRow, 1).toString();
		        Member mem = msi.checkMemberNo(MemberNo);	//轉型態給deleteMember用
		        
		        
		     // 跳出確認視窗
		        int result = JOptionPane.showConfirmDialog(
		                Manager.this,
		                "確定要刪除該筆會員嗎？",
		                "刪除確認",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.WARNING_MESSAGE
		        );
		        
		        if (result == JOptionPane.YES_OPTION)
		        {
		        	
		        	msi.deleteMember(mem);
		        	
		        	List<Member> list = msi.selectAllMember();
		        	memberModel.setRowCount(0);// 先清空表格
	        		for(Member m: list)
	        		{
	        			memberModel.addRow(new Object[]{m.getId(), m.getMember_no(), m.getName(), m.getUsername(),
	                                           m.getPassword(), m.getEmail(), m.getPhone(), m.getAddress()});
	        		}
		        	JOptionPane.showMessageDialog(Manager.this, "會員已刪除！");
		        }
		        else {
	                JOptionPane.showMessageDialog(Manager.this, "刪除失敗，請再試一次。");
	            }
        		
        	}
        });
        deleteMemberbtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        deleteMemberbtn.setBounds(935, 347, 130, 42);
        if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
        	memberPanel.add(deleteMemberbtn);
		}
        
        
        
        
              
		
		// ===== 分頁 4: 員工管理 =====
        JPanel employeePanel = new JPanel();
        employeePanel.setBackground(new Color(240, 240, 240));
        employeePanel.setLayout(null);
        tabbedPane.addTab("員工管理", employeePanel);

        
        List<Employee> listEmployee = esi.findAllEmployee();
        String[] employeeColumns = {"ID","員工編號","姓名","帳號","密碼","角色","入職時間","離職時間"};
        employeeModel = new DefaultTableModel(employeeColumns,0);
        
        JTable empTable = new JTable(employeeModel);
        empTable.setDefaultEditor(Object.class, null); // 禁用編輯
        empTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        empTable.setRowHeight(25);
        
        JScrollPane scrollPane_2 = new JScrollPane(empTable);
        scrollPane_2.setBounds(10, 62, 1053, 267);
        employeePanel.add(scrollPane_2);
        
        
     // 初始化資料
        for(Employee em : listEmployee){
            employeeModel.addRow(new Object[]{em.getId(), em.getEmployee_no(), em.getName(), em.getUsername(),
                                        em.getPassword(), em.getRole(), em.getArrived_date(), em.getLeaved_date()});
        }
        
        
        
        searchEmployee = new JTextField();
        searchEmployee.setFont(new Font("新細明體", Font.PLAIN, 14));
        searchEmployee.setColumns(10);
        searchEmployee.setBounds(10, 10, 269, 42);
        employeePanel.add(searchEmployee);
        
        
    //**************Button*******************    
        JButton searchEmployeebtn = new JButton("搜尋");
        searchEmployeebtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String keyword = searchEmployee.getText().trim();
        		List<Employee> list = esi.keyword(keyword);
        		employeeModel.setRowCount(0);// 先清空表格
        		for(Employee em: list)
        		{
        			employeeModel.addRow(new Object[]{em.getId(), em.getEmployee_no(), em.getName(), em.getUsername(),
                            em.getPassword(), em.getRole(), em.getArrived_date(), em.getLeaved_date()});
        		}
	
        	}
        });
        searchEmployeebtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        searchEmployeebtn.setBounds(292, 10, 96, 42);
        employeePanel.add(searchEmployeebtn);
        
        
        JButton insertEmployeebtn = new JButton("新增員工");
        insertEmployeebtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddEmployee addemployee = new AddEmployee();
        		addemployee.setVisible(true);
        	}
        });
        insertEmployeebtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        insertEmployeebtn.setBounds(933, 10, 130, 42);
        if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
        	employeePanel.add(insertEmployeebtn);
		}
        
        
        
        
        JButton updateEmployeebtn = new JButton("修改資料");
        updateEmployeebtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int selectedRow = empTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆資料！");
		            return;
		        }
		        
		        String EmployeNo = empTable.getValueAt(selectedRow, 1).toString();
		        new UpdateEmployeeUI(EmployeNo).setVisible(true);
        		
        	}
        });
        updateEmployeebtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        updateEmployeebtn.setBounds(10, 346, 130, 42);
        if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
        	employeePanel.add(updateEmployeebtn);
		}
        
        
        JButton deleteEmployeebtn = new JButton("離職");
        deleteEmployeebtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int selectedRow = empTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(Manager.this, "請先選擇一筆資料！");
		            return;
		        }
		        
		        String EmployeNo = empTable.getValueAt(selectedRow, 1).toString();
		        Employee em = esi.checkEmployeeNo(EmployeNo);
		        
		        // 跳出確認視窗
		        int result = JOptionPane.showConfirmDialog(
		                Manager.this,
		                "該員工確定離職嗎？",
		                "離職確認",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.WARNING_MESSAGE
		        );
		        if (result == JOptionPane.YES_OPTION)
		        {
		        	esi.updateLeaveTime(em);
		        	
		        	List<Employee> list = esi.findAllEmployee();
		        	employeeModel.setRowCount(0);// 先清空表格
		        	for(Employee emp : list)
			    	{
			    		Manager.employeeModel.addRow(new Object[]{emp.getId(), emp.getEmployee_no(), emp.getName(), emp.getUsername(),
			    				emp.getPassword(), emp.getRole(), emp.getArrived_date(), emp.getLeaved_date()});
			    	}
		        	
		        	JOptionPane.showMessageDialog(Manager.this, "員工已離職！");
		        	
		        }
		        else {
	                JOptionPane.showMessageDialog(Manager.this, "刪除失敗，請再試一次。");
	            }
			
        	}
        });
        deleteEmployeebtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        deleteEmployeebtn.setBounds(935, 346, 130, 42);
        if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
        	employeePanel.add(deleteEmployeebtn);
		}
        
        
		
		
		
		
		// ===== 分頁 5: 報表管理 =====
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(new Color(240, 240, 240));
        tabbedPane.addTab("報表管理", reportPanel);
        
        
     // 報表按鈕
        JButton salesReportBtn = new JButton("生成銷售報表");
        salesReportBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        salesReportBtn.setBounds(20, 20, 159, 48);
        JButton orderReportBtn = new JButton("生成訂單報表");
        orderReportBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        orderReportBtn.setBounds(215, 20, 159, 48);
        JButton memberReportBtn = new JButton("生成會員報表");
        memberReportBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        memberReportBtn.setBounds(410, 20, 159, 48);

        // ====== 綁定事件 ======

        // 1. 銷售報表
        salesReportBtn.addActionListener(e -> {
            try {
                List<Porder> orders = psi.findAllPorder();            // 從 service/dao 拿訂單
                List<Porder_Detail> details = psi.findAllPorderDetail(); // 拿訂單明細
                CreateExecl excelUtil = new CreateExecl();
                String filePath = "sales_report.xlsx";
                excelUtil.createSalesReport(filePath, details, orders);

                JOptionPane.showMessageDialog(this, "✅ 銷售報表已生成！\n位置：" + filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "生成失敗：" + ex.getMessage(),
                    "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 2. 訂單報表
        orderReportBtn.addActionListener(e -> {
            try {
            	List<Porder> orders = psi.findAllPorder();
            	List<Porder_Detail> details = psi.findAllPorderDetail();
                CreateExecl excelUtil = new CreateExecl();
                String filePath = "order_report.xlsx";
                excelUtil.createOrderReport(filePath, orders, details);

                JOptionPane.showMessageDialog(this, "✅ 訂單報表已生成！\n位置：" + filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "生成失敗：" + ex.getMessage(),
                    "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 3. 會員報表（你之前 insertMember 已經有了）
        memberReportBtn.addActionListener(e -> {
            try {
                List<Member> members = msi.selectAllMember();
                CreateExecl excelUtil = new CreateExecl();
                String[] titles = {"姓名", "帳號", "密碼", "Email", "電話", "地址"};
                String filePath = "member_report.xls";
                excelUtil.createExecl(filePath, "會員報表", titles);
                excelUtil.insertMember(filePath, "會員報表", members);

                JOptionPane.showMessageDialog(this, "✅ 會員報表已生成！\n位置：" + filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "生成失敗：" + ex.getMessage(),
                    "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });
        reportPanel.setLayout(null);

        // 把按鈕丟進 panel
        reportPanel.add(salesReportBtn);
        reportPanel.add(orderReportBtn);
        reportPanel.add(memberReportBtn);

        // 最後加到 TabbedPane 的第 5 頁
        tabbedPane.addTab("報表管理", reportPanel);
        
	}
}
