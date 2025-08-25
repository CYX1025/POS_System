package controller.porder;

import dao.impl.ProductDaoImpl;
import model.Employee;
import model.Member;
import model.Porder_Detail;
import model.Product;
import util.Session;
import util.Tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class QuantityUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel QuantityPanel;
    private JLabel ProductName;
    private JLabel Price;
    private JLabel lblQuantity;
    private int quantity = 0;
    private Product product;
    
    
    public QuantityUI(String productId) {
    	
    	Member sessionMember = Session.getMember();
    	Employee sessionEmployee = Session.getEmployee();
    	
        setTitle("選擇商品數量");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 599, 480);
        setLocationRelativeTo(null); // 置中視窗
        
        QuantityPanel = new JPanel();
        QuantityPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(QuantityPanel);

        // 查詢商品資訊
        ProductDaoImpl pdi = new ProductDaoImpl();
        product = pdi.findById(productId);

        if (product == null) {
            JOptionPane.showMessageDialog(this, "查無商品資訊", "錯誤", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        QuantityPanel.setLayout(null);

        ProductName = new JLabel(product.getProduct_name());
        ProductName.setHorizontalAlignment(SwingConstants.CENTER);
        ProductName.setBounds(0, 15, 583, 63);
        ProductName.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        QuantityPanel.add(ProductName);

        Price = new JLabel("商品價格: $" + product.getPrice());
        Price.setBounds(15, 99, 553, 74);
        Price.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        QuantityPanel.add(Price);

        // 數量區塊
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBounds(0, 183, 583, 74);
        quantityPanel.setLayout(null);
        QuantityPanel.add(quantityPanel);
        
        
        JButton btnMinus = new JButton("-");
        btnMinus.setFont(new Font("新細明體", Font.BOLD, 16));
        btnMinus.setBounds(166, 10, 76, 59);
        quantityPanel.add(btnMinus);
        btnMinus.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (quantity > 0) {
                    quantity--;
                    lblQuantity.setText(String.valueOf(quantity));
                }
        	}
        });
        
        lblQuantity = new JLabel(" "+String.valueOf(quantity));
        lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantity.setFont(new Font("新細明體", Font.BOLD, 20));
        lblQuantity.setBounds(252, 12, 49, 55);
        quantityPanel.add(lblQuantity);
        
        
        JButton btnPlus = new JButton("+");
        btnPlus.setFont(new Font("新細明體", Font.BOLD, 16));
        btnPlus.setBounds(308, 10, 76, 59);
        quantityPanel.add(btnPlus);
        btnPlus.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		quantity++;
                lblQuantity.setText(String.valueOf(quantity));
        	}
        });
        
       

        // 確認按鈕
        JButton btnConfirm = new JButton("確認");
        btnConfirm.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		 if(quantity == 0)
        		 {
        			 dispose();
        			 return;
        		 }
        		 else if(quantity > 0)
        		 {
        			// 使用已查詢的 product
         	        Porder_Detail detail = new Porder_Detail();
         	        detail.setProduct_id(product.getProduct_id());
         	        detail.setProduct_name(product.getProduct_name());
         	        detail.setPrice(product.getPrice());
         	        detail.setQuantity(quantity);
         	        
         	       if(Session.isEmployeeLogin())
         	       {
         	    	   detail.setEmployee(sessionEmployee.getEmployee_no());
         	       }
         	       else if (Session.isMemberLogin())
         	       {
         	    	  detail.setMember(sessionMember.getMember_no());
         	       }
         	           
         	        
         	        //  檢查是否已存在該商品，若存在則更新數量
         	       boolean found = false;
                   for (Porder_Detail item : PosUI.cart) {
                       if (item.getProduct_id().equals(detail.getProduct_id())) {
                           item.setQuantity(item.getQuantity() + detail.getQuantity());
                           found = true;
                           break;
                       }
                   }

                   if (!found) {
                       PosUI.cart.add(detail);
                   }
                   PosUI.refreshCartTable();

                   // 寫入檔案並更新畫面
                   Tool.writeFile("porderdetail.txt", PosUI.cart);
                   

                   JOptionPane.showMessageDialog(null, "新增成功：" + product.getProduct_name() + " x " + quantity);
                   dispose();
        		 }       
        	}
        });
        btnConfirm.setFont(new Font("新細明體", Font.BOLD, 18));
        btnConfirm.setBounds(15, 267, 553, 74);
        QuantityPanel.add(btnConfirm);


        
    }
}
