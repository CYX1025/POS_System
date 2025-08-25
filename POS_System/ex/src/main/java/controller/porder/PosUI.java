package controller.porder;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.member.selectMemberUI;
import model.Employee;
import model.Member;
import model.Porder_Detail;
import util.Session;
import util.Tool;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PosUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable cartTable;
	private static DefaultTableModel cartModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PosUI frame = new PosUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static List<Porder_Detail> cart = new ArrayList<>();
	
	
	
	
	public static void refreshCartTable() {
		
	    cartModel.setRowCount(0); // 清空 JTable
	    for (Porder_Detail detail : cart) {
	        int subtotal = detail.getPrice() * detail.getQuantity();
	        cartModel.addRow(new Object[]{
	            detail.getProduct_name(),
	            detail.getPrice(),
	            detail.getQuantity(),
	            subtotal
	        });
	    }
	}



	/**
	 * Create the frame.
	 */
	public PosUI() {
		
		Member sessionMember = Session.getMember();
		Employee sessionEmployee = Session.getEmployee();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 648);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 242, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("POS 銷售系統");
		setSize(1027, 600);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 992, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel showLoginUser = new JLabel();
		showLoginUser.setBounds(10, 0, 340, 49);
		panel.add(showLoginUser);
		showLoginUser.setFont(new Font("新細明體", Font.BOLD, 20));
		
		if (Session.isMemberLogin())
		{
			showLoginUser.setText("登入會員: "+sessionMember.getUsername()+"  "+sessionMember.getName());
		}
		if(Session.isEmployeeLogin())
		{
			showLoginUser.setText("登入員工: "+sessionEmployee.getUsername()+"  "+sessionEmployee.getName());
		}
			
		JLabel lblNewLabel = new JLabel("POS銷售系統");
		lblNewLabel.setBounds(375, 0, 130, 49);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel cupItem = new JPanel();
		cupItem.setBackground(new Color(255, 255, 255));
		cupItem.setBounds(159, 69, 452, 438);
		contentPane.add(cupItem);
		cupItem.setLayout(null);
		
		//Jtable
		cartModel = new DefaultTableModel(new Object[]{"商品名稱", "單價", "數量", "小計"}, 0);
		cartTable = new JTable(cartModel);
		
		// 表格內容字體
		cartTable.setFont(new Font("微軟正黑體", Font.PLAIN, 12)); 
		cartTable.setRowHeight(28);

		// 表頭字體
		cartTable.getTableHeader().setFont(new Font("微軟正黑體", Font.BOLD, 18));
		
		JScrollPane cartScroll = new JScrollPane(cartTable);
		cartScroll.setBounds(624, 69, 378, 438);
		contentPane.add(cartScroll);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 69, 139, 438);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("商品分類");
		lblNewLabel_2.setForeground(new Color(160, 160, 160));
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_2.setBounds(25, 10, 83, 34);
		panel_1.add(lblNewLabel_2);
		
		
		LocalDateTime now=LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = now.format(formatter);
		JLabel timeSet = new JLabel();		//時間
		timeSet.setBounds(790, 0, 192, 49);
		panel.add(timeSet);
		timeSet.setFont(new Font("新細明體", Font.BOLD, 20));
		timeSet.setText(""+formatted);
		Timer timer = new Timer(1000, new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               LocalDateTime now = LocalDateTime.now();
	               String formatted = now.format(formatter);
	               timeSet.setText(formatted);
	           }
	       });
	       timer.start(); // 啟動計時器
		
	     //*****************************************************************************		
	   			
	
		
		
		
		//主餐類
		JPanel mainCoursePanel = new JPanel(null);
		mainCoursePanel.setBounds(0, 0, 452, 438);
		cupItem.add(mainCoursePanel);
		mainCoursePanel.setVisible(true);
		
		//**************義大利麵******************
		JLabel pasta = new JLabel("義大利麵");
		pasta.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		pasta.setBounds(20, 10, 128, 42);
		mainCoursePanel.add(pasta);
		
		RoundedButton trufflePasta = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		trufflePasta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("fp1").setVisible(true);
			}
		});
		trufflePasta.setText("松露義大利麵");
		trufflePasta.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		trufflePasta.setBounds(20, 62, 128, 72);
		mainCoursePanel.add(trufflePasta);
		
		RoundedButton butterPasta = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		butterPasta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("fp2").setVisible(true);
			}
		});
		butterPasta.setText("奶油義大利麵");
		butterPasta.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		butterPasta.setBounds(158, 62, 128, 72);
		mainCoursePanel.add(butterPasta);
		
		
		//酒精類	
		JPanel alcoholPanel = new JPanel(null);
		alcoholPanel.setBounds(0, 0, 452, 438);
		cupItem.add(alcoholPanel);
		alcoholPanel.setVisible(false);
		
		//******威士忌******
		JLabel whiskey = new JLabel("威士忌");
		whiskey.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		whiskey.setBounds(20, 10, 70, 42);
		alcoholPanel.add(whiskey);
		
		RoundedButton Manhattan = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Manhattan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("aw1").setVisible(true);
			}
		});
		Manhattan.setText("曼哈頓");
		Manhattan.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Manhattan.setBounds(20, 62, 128, 72);
		alcoholPanel.add(Manhattan);
		
		RoundedButton OldFashioned = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		OldFashioned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("aw2").setVisible(true);
			}
		});
		OldFashioned.setText("古典雞尾酒");
		OldFashioned.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		OldFashioned.setBounds(158, 62, 128, 72);
		alcoholPanel.add(OldFashioned);
		
		RoundedButton WhiskeySour = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		WhiskeySour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("aw3").setVisible(true);
			}
		});
		WhiskeySour.setText("威士忌酸酒");
		WhiskeySour.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		WhiskeySour.setBounds(296, 62, 128, 72);
		alcoholPanel.add(WhiskeySour);
		
		//*************伏特加********************
		JLabel Vodka = new JLabel("伏特加");
		Vodka.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Vodka.setBounds(20, 144, 70, 42);
		alcoholPanel.add(Vodka);
		
		RoundedButton Cosmopolitan = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Cosmopolitan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("av1").setVisible(true);
			}
		});
		Cosmopolitan.setText("柯夢波丹");
		Cosmopolitan.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Cosmopolitan.setBounds(20, 196, 128, 72);
		alcoholPanel.add(Cosmopolitan);
		
		RoundedButton BloodyMary = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		BloodyMary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("av2").setVisible(true);
			}
		});
		BloodyMary.setText("血腥瑪麗");
		BloodyMary.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		BloodyMary.setBounds(158, 196, 128, 72);
		alcoholPanel.add(BloodyMary);
		
		RoundedButton MoscowMule = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		MoscowMule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("av3").setVisible(true);
			}
		});
		MoscowMule.setText("莫斯科騾子");
		MoscowMule.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		MoscowMule.setBounds(296, 196, 128, 72);
		alcoholPanel.add(MoscowMule);
		
		//****************龍舌蘭*******************
		JLabel Tequila = new JLabel("龍舌蘭");
		Tequila.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Tequila.setBounds(20, 292, 70, 42);
		alcoholPanel.add(Tequila);
		
		RoundedButton Margarita = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Margarita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("at1").setVisible(true);
			}
		});
		Margarita.setText("瑪格麗特");
		Margarita.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Margarita.setBounds(20, 344, 128, 72);
		alcoholPanel.add(Margarita);
		
		RoundedButton TequilaSunrise = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		TequilaSunrise.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("at2").setVisible(true);
			}
		});
		TequilaSunrise.setText("龍舌蘭日出");
		TequilaSunrise.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		TequilaSunrise.setBounds(158, 344, 128, 72);
		alcoholPanel.add(TequilaSunrise);
		
		RoundedButton Paloma = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Paloma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("at3").setVisible(true);
			}
		});
		Paloma.setText("帕洛瑪");
		Paloma.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Paloma.setBounds(296, 344, 128, 72);
		alcoholPanel.add(Paloma);
		
		
		//軟飲類
		JPanel softDrinkPanel = new JPanel(null);
		softDrinkPanel.setBounds(0, 0, 452, 438);
		cupItem.add(softDrinkPanel);
		softDrinkPanel.setVisible(false);
		
		//**********氣跑飲************
		JLabel soda = new JLabel("氣跑飲");
		soda.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		soda.setBounds(20, 10, 70, 42);
		softDrinkPanel.add(soda);
		
		RoundedButton Cola = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Cola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("ds1").setVisible(true);
			}
		});
		Cola.setText("可樂");
		Cola.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Cola.setBounds(20, 62, 128, 72);
		softDrinkPanel.add(Cola);
		
		RoundedButton Sprite = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		Sprite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("ds2").setVisible(true);
			}
		});
		Sprite.setText("雪碧");
		Sprite.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		Sprite.setBounds(158, 62, 128, 72);
		softDrinkPanel.add(Sprite);
		
		
		//點心類
		JPanel snackPanel = new JPanel(null);
		snackPanel.setBounds(0, 0, 452, 438);
		cupItem.add(snackPanel);
		snackPanel.setVisible(false);
		
		//*******************炸物*******************
		JLabel Fried = new JLabel("炸物");
		Fried.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Fried.setBounds(20, 10, 70, 42);
		snackPanel.add(Fried);
		
		RoundedButton frenchFries = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		frenchFries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("df1").setVisible(true);
			}
		});
		frenchFries.setText("薯條");
		frenchFries.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		frenchFries.setBounds(20, 62, 128, 72);
		snackPanel.add(frenchFries);
		
		RoundedButton friedChicken = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		friedChicken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new QuantityUI("df2").setVisible(true);
			}
		});
		friedChicken.setText("炸雞");
		friedChicken.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		friedChicken.setBounds(158, 62, 128, 72);
		snackPanel.add(friedChicken);
		
		
		
       
	       
//*********************button***************************
	    RoundedButton alcohol = new RoundedButton(new Color(0x2894FF), Color.GRAY );
	    alcohol.setText("酒精");
	    alcohol.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
	    alcohol.setBounds(10, 136, 119, 57);
		panel_1.add(alcohol);
	    alcohol.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		alcoholPanel.setVisible(true);
	    		softDrinkPanel.setVisible(false);
	    		snackPanel.setVisible(false);
	    		mainCoursePanel.setVisible(false);
	    	}
	    });
	    
		
		RoundedButton softDrink = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		softDrink.setText("軟飲");
		softDrink.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		softDrink.setBounds(10, 219, 119, 57);
		panel_1.add(softDrink);
		softDrink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alcoholPanel.setVisible(false);
	    		softDrinkPanel.setVisible(true);
	    		snackPanel.setVisible(false);
	    		mainCoursePanel.setVisible(false);
			}
		});
		
		
		RoundedButton mainCourse = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		mainCourse.setText("主餐");
		mainCourse.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		mainCourse.setBounds(10, 54, 119, 57);
		panel_1.add(mainCourse);
		mainCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alcoholPanel.setVisible(false);
				softDrinkPanel.setVisible(false);
				snackPanel.setVisible(false);
				mainCoursePanel.setVisible(true);
			}
		});
		
		
		RoundedButton snack = new RoundedButton(new Color(40, 148, 255), Color.GRAY);
		snack.setText("點心");
		snack.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		snack.setBounds(10, 301, 119, 57);
		panel_1.add(snack);
		snack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alcoholPanel.setVisible(false);
				softDrinkPanel.setVisible(false);
				snackPanel.setVisible(true);
				mainCoursePanel.setVisible(false);
			}
		});
		
		
	     
	    JButton check = new JButton("下一步");
	    check.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (cart.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "目前沒有任何商品", "提示", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	    		
	    		Tool.saveFile(cart, "porderdetail.txt"); //儲存確定資料

	    		//如果是員工登入，有儲存會員資料，則在這裡加
	    		if(Session.isEmployeeLogin())
	    		{
	    			if(Session.isMemberLogin())
	    			{
	    				@SuppressWarnings("unchecked")
	    			    List<Porder_Detail> list = (List<Porder_Detail>) Tool.readFile("porderdetail.txt");
	    			    
	    			    if (list == null) {
	    			        list = new ArrayList<>();
	    			    }
	    				for(Porder_Detail detail : list)
	    				{
	    					Member sessionMember = Session.getMember(); // 重新抓
	    					System.out.println("cart=" + cart);
	    					System.out.println("sessionMember=" + sessionMember);
	    					if (sessionMember != null)
	    					{
	    						detail.setMember(sessionMember.getMember_no());
	    					}
	    					else 
	    					{
	    				        detail.setMember(""); // 或 "GUEST"，代表沒有會員
	    					}
	    					
	    					
	    					//讓每一筆更新會員資料，再回寫回去
	    				}
	    				Tool.saveFile(list, "porderdetail.txt");
	    			}
	    		}
	    		
	    		
	    		
	    		
	    		ConfirmUI confirm = new ConfirmUI();
	            confirm.setVisible(true);
	            dispose();
	    	}
	    });
		check.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		check.setBounds(863, 517, 139, 34);
		contentPane.add(check);
		
		JButton modifybtn = new JButton("修改數量");
		modifybtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		            int row = cartTable.getSelectedRow();
		            if (row >= 0) {
		                // 取出該筆的商品資料
		                Porder_Detail detail = cart.get(row);

		                // 跳出輸入框，輸入新的數量
		                String input = JOptionPane.showInputDialog(
		                    null,
		                    "請輸入新的數量：",
		                    detail.getQuantity()
		                );

		                if (input != null) {
		                    try {
		                        int newQty = Integer.parseInt(input);
		                        if (newQty > 0) {
		                            detail.setQuantity(newQty);  // 更新數量
		                            refreshCartTable();     // 更新 JTable
		                            Tool.writeFile("porderdetail.txt", cart); // 存回檔案
		                        } else {
		                            JOptionPane.showMessageDialog(null, "數量必須大於 0！");
		                        }
		                    } catch (NumberFormatException ex) {
		                        JOptionPane.showMessageDialog(null, "請輸入正確的數字！");
		                    }
		                }
		            }
		        }
				

			
		});
		modifybtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		modifybtn.setBounds(624, 517, 139, 34);
		contentPane.add(modifybtn);
		
		JButton selectMemberbtn = new JButton("選擇會員資料");
		selectMemberbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new selectMemberUI().setVisible(true);
				
				
			}
		});
		selectMemberbtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		selectMemberbtn.setBounds(433, 517, 178, 34);
		if(Session.isEmployeeLogin())
		{
			contentPane.add(selectMemberbtn);
		}

		
		
		JButton check_1 = new JButton("回管理頁");
		check_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cart.clear();           
				Manager pordermanager = new Manager();
    			pordermanager.setVisible(true);
    			dispose();
			}
		});
		check_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		check_1.setBounds(10, 517, 139, 34);
		if (Session.isEmployeeLogin()) {
			contentPane.add(check_1);
		}
	        
	        
	        
		
		

	}
}
