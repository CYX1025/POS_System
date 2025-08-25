package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Porder_DetailDao;
import model.Porder_Detail;
import util.DbConnection;



public class Porder_DetailDaoImpl implements Porder_DetailDao {
	
    public static void main(String[] args) {

    	/*
    	//addList(List<Porder_Detail> details)
    	String orderNo = auto_numbering.OrderNo();
    	List<Porder_Detail> list = new ArrayList<>();
    	list.add(new Porder_Detail(orderNo,"P001", "aaa", 200, 5, "aaa", ""));
    	list.add(new Porder_Detail(orderNo,"P002", "bbb", 300, 2, "aaa", ""));
    	new Porder_DetailDaoImpl().addList(list);
    	*/
    	/*
    	//add(Porder_Detail detail)
    	String orderNo = auto_numbering.OrderNo();
    	Porder_DetailDao detailDao = new Porder_DetailDaoImpl();
    	Porder_Detail d1 = new Porder_Detail(orderNo, "ds1", 2, "小明");
    	Porder_Detail d2 = new Porder_Detail(orderNo, "P002", 1, "小明");
    	Porder_Detail d3 = new Porder_Detail(orderNo, "P003", 5, "小明");

    	detailDao.add(d1);
    	detailDao.add(d2);
    	detailDao.add(d3);
    	*/
    }
    
    private static Connection conn = DbConnection.getDb();
    
    
	public void add(Porder_Detail detail) {
		String sql = "INSERT INTO porder_detail (order_no, product_id, product_name, price, quantity, member, employee) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
       
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, detail.getOrder_no());
            ps.setString(2, detail.getProduct_id());
            ps.setString(3, detail.getProduct_name());
            ps.setInt(	 4, detail.getPrice());
            ps.setInt(	 5, detail.getQuantity());
            ps.setString(6, detail.getMember());
            ps.setString(7, detail.getEmployee());
            ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
		
	}


	@Override
	public void addList(List<Porder_Detail> details) {
		String sql = "INSERT INTO porder_detail (order_no, product_id, product_name, price, quantity,member, employee) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for (Porder_Detail detail : details) {
				ps.setString(1, detail.getOrder_no());
	            ps.setString(2, detail.getProduct_id());
	            ps.setString(3, detail.getProduct_name());
	            ps.setInt(	 4, detail.getPrice());
	            ps.setInt(	 5, detail.getQuantity());
	            ps.setString(6, detail.getMember());
	            ps.setString(7, detail.getEmployee());
				ps.addBatch(); // 加入批次
	        }

			ps.executeBatch(); // 一次送出
	        System.out.println("成功寫入 " + details.size() + " 筆明細");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public List<Porder_Detail> selectAll() {
		String sql = "select * from Porder_Detail";
		List<Porder_Detail> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Porder_Detail pd = new Porder_Detail();
				pd.setOrder_no(rs.getString("order_no"));
				pd.setProduct_id(rs.getString("product_id"));
				pd.setProduct_name(rs.getString("product_name"));
				pd.setPrice(rs.getInt("price"));
				pd.setQuantity(rs.getInt("quantity"));
				pd.setMember(rs.getString("member"));
				pd.setEmployee(rs.getString("employee"));
				list.add(pd);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public Porder_Detail SelectByOrder_No(String Order_No) {
		String sql = "select * from Porder_Detail where order_no = ?";
		Porder_Detail pd = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Order_No);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				pd = new Porder_Detail();
				pd.setOrder_no(rs.getString("order_no"));
				pd.setProduct_id(rs.getString("product_id"));
				pd.setProduct_name(rs.getString("product_name"));
				pd.setPrice(rs.getInt("price"));
				pd.setQuantity(rs.getInt("quantity"));
				pd.setMember(rs.getString("member"));
				pd.setEmployee(rs.getString("employee"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pd;
	}


	@Override
	public List<Porder_Detail> AllOrder_No_Detail(String orderNo) {
		String sql = "SELECT * FROM Porder_Detail WHERE order_no = ?";
		List<Porder_Detail> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Porder_Detail pd = new Porder_Detail();
				pd.setId(rs.getInt("id"));
				pd.setOrder_no(rs.getString("order_no"));
				pd.setProduct_id(rs.getString("product_id"));
				pd.setProduct_name(rs.getString("product_name"));
				pd.setPrice(rs.getInt("price"));
				pd.setQuantity(rs.getInt("quantity"));
				pd.setMember(rs.getString("member"));
				pd.setEmployee(rs.getString("employee"));

				list.add(pd); // 加入集合中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void updateQuantityById(int id, int quantity) {
		String sql = "update porder_detail set quantity = ? where id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void delete(String order_no) {
		String sql = "delete from Porder_Detail where order_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, order_no);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	

	@Override
	public void deleteById(int id) {
		String sql = "delete from porder_detail where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
	        ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

