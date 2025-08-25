package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.PorderDao;
import model.Porder;
import util.DbConnection;
import util.auto_numbering;


public class PorderDaoImpl implements PorderDao{

	public static void main(String[] args) {
		String orderNo = auto_numbering.OrderNo();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Porder order = new Porder(orderNo, now);

		// 寫入主檔
		PorderDao porderDao = new PorderDaoImpl();
		porderDao.add(order);

	}
	private static Connection conn = DbConnection.getDb();

	@Override
	public void add(Porder porder) {
		String sql = "INSERT INTO porder(order_no, created_time) VALUES(?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, porder.getOrder_no());
            ps.setTimestamp(2, porder.getCreate_time());
            ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Porder> selectAll() {
		String sql = "select * from porder";
		List<Porder> list= new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Porder p = new Porder();
				p.setOrder_no(rs.getString("order_no"));
				p.setCreate_time(rs.getTimestamp("created_time"));
				list.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Porder SelectByOrder_No(String Order_No) {
		String sql = "select * from porder where order_no = ?";
		Porder p = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Order_No);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				p = new Porder();
				p.setOrder_no(rs.getString("order_no"));
				p.setCreate_time(rs.getTimestamp("created_time"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	@Override
	public List<Porder> keyword(String keyword) {
		List<Porder> list = new ArrayList<>();
		String sql = "select * from porder where order_no like ? or created_time like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			String key = "%"+keyword+"%";
			ps.setString(1, key);
			ps.setString(2, key);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Porder p = new Porder();
				p.setOrder_no(rs.getString("order_no"));
				p.setCreate_time(rs.getTimestamp("created_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public void delete(String order_no) {
		String sql = "delete from Porder where order_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, order_no);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
