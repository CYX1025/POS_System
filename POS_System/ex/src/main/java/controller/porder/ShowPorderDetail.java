package controller.porder;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Porder_Detail;
import service.impl.PorderServiceImpl;
import util.Session;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ShowPorderDetail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable detailTable;
	private DefaultTableModel detailModel;
    private String orderNo;
    private static PorderServiceImpl psi = new PorderServiceImpl();

	/**
	 * Create the frame.
	 */
	public ShowPorderDetail(String orderNo) {
		this.orderNo = orderNo;
		
		setTitle("訂單明細 - " + orderNo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 852, 614);
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 標題
        JLabel lblTitle = new JLabel("訂單編號: " + orderNo, JLabel.CENTER);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        lblTitle.setBounds(10, 10, 800, 40);
        contentPane.add(lblTitle, BorderLayout.NORTH);
        
        
        List<Porder_Detail> list = psi.findPorderDetailByOrderNo(orderNo);
     // JTable
        String[] columns = {"ID", "商品名稱", "單價", "數量"};
        detailModel = new DefaultTableModel(columns, 0);
        
        
        
        detailModel.setRowCount(0);
        for (Porder_Detail detail : list) {
            detailModel.addRow(new Object[]{
                    detail.getId(),
                    detail.getProduct_name(),
                    detail.getPrice(),
                    detail.getQuantity()
            });
        }

		detailTable = new JTable(detailModel);
		detailTable.setRowHeight(25);
		detailTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		detailTable.setDefaultEditor(Object.class, null); // 禁用編輯
		
		JScrollPane scrollPane = new JScrollPane(detailTable);
		scrollPane.setBounds(10, 52, 816, 350);
		contentPane.add(scrollPane);
		
		
		
		
	//****************Button************************
		
		JButton updatebtn = new JButton("修改品項數量");
		updatebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "目前沒有商品可修改");
		            return;
		        }
				
				// 下拉選單: 商品名稱
		        String[] productNames = list.stream().map(Porder_Detail::getProduct_name).toArray(String[]::new);
		        JComboBox<String> comboBox = new JComboBox<>(productNames);

		        // 數量輸入
		        JTextField txtQuantity = new JTextField(5);

		        JPanel panel = new JPanel();
		        panel.add(new JLabel("商品:"));
		        panel.add(comboBox);
		        panel.add(new JLabel("數量:"));
		        panel.add(txtQuantity);

		        int result = JOptionPane.showConfirmDialog(null, panel, "修改商品數量", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                String selectedProduct = (String) comboBox.getSelectedItem();
		                int quantity = Integer.parseInt(txtQuantity.getText());

		                // 找到選擇的明細
		                Porder_Detail selectedDetail = list.stream()
		                        .filter(d -> d.getProduct_name().equals(selectedProduct))
		                        .findFirst()
		                        .orElse(null);

		                if (selectedDetail != null) {
		                    psi.updatePorderDetailByQuantity(selectedDetail.getId(), quantity);
		                    selectedDetail.setQuantity(quantity);

		                    detailModel.setRowCount(0);
		                    for (Porder_Detail detail : list) {
		                        detailModel.addRow(new Object[]{
		                                detail.getId(),
		                                detail.getProduct_name(),
		                                detail.getPrice(),
		                                detail.getQuantity()
		                        });
		                    }
		                    
		                    JOptionPane.showMessageDialog(null, "修改完成！");
		                }

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "數量必須是數字");
		            }
		        }
				
				
			}
		});
		updatebtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		updatebtn.setBounds(10, 412, 143, 48);
		if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
			contentPane.add(updatebtn);
		}
		
		
		
		
		
		JButton deletebtn = new JButton("刪除品項");
		deletebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "目前沒有商品可刪除");
		            return;
		        }
				
				String[] productNames = list.stream().map(Porder_Detail::getProduct_name).toArray(String[]::new);
		        JComboBox<String> comboBox = new JComboBox<>(productNames);

		        JPanel panel = new JPanel();
		        panel.add(new JLabel("選擇商品:"));
		        panel.add(comboBox);
		        int result = JOptionPane.showConfirmDialog(null, panel, "刪除商品", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            String selectedProduct = (String) comboBox.getSelectedItem();

		            Porder_Detail selectedDetail = list.stream()
		                    .filter(d -> d.getProduct_name().equals(selectedProduct))
		                    .findFirst()
		                    .orElse(null);

		            if (selectedDetail != null) {
		                psi.deletePorderDetailItem(selectedDetail.getId());
		                list.remove(selectedDetail);

		                detailModel.setRowCount(0);
		                for (Porder_Detail detail : list) {
	                        detailModel.addRow(new Object[]{
	                                detail.getId(),
	                                detail.getProduct_name(),
	                                detail.getPrice(),
	                                detail.getQuantity()
	                        });
	                    }
		                JOptionPane.showMessageDialog(null, "刪除完成！");
		            }
		        }
			}
		});
		deletebtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		deletebtn.setBounds(683, 412, 143, 48);
		if (Session.isEmployeeLogin() 
		        && Session.getEmployee() != null 
		        && "admin".equalsIgnoreCase(Session.getEmployee().getRole())) {
			contentPane.add(deletebtn);
		}
		

		
		
		JButton closebtn = new JButton("關閉");
		closebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		closebtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		closebtn.setBounds(683, 517, 143, 48);
		contentPane.add(closebtn);

	}
}
