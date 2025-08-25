package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DbConnection;

public class ProductDaoImpl implements ProductDao{

	public static void main(String[] args) {
		
		
		/*//執行addProduct(Product product)
		Product p = new Product("aaaa", "aaa", "aaa","a",333);
		new ProductDaoImpl().addProduct(p);*/
		

		/*//List<Product> findAllProduct()
		List<Product> l = new ProductDaoImpl().findAllProduct();
		
		for(Product p: l)
		{
			System.out.println(p.getProduct_name());
		}
		/
*/
	}

	private static Connection conn = DbConnection.getDb();
	
	//insert
	
	@Override
	public void addProduct(Product product) {
		String sql = "insert into product(product_id, product_name, product_type, product_type1, price) values(?, ?, ?, ?, ?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProduct_id());
			ps.setString(2, product.getProduct_name());
			ps.setString(3, product.getProduct_type());
			ps.setString(4, product.getProduct_type1());
			ps.setInt(5, product.getPrice());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//select
	
	@Override
	public List<Product> selectAllProduct() {
		String sql = "select * from product";
		List<Product> list = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setProduct_id(rs.getString("product_id"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_type(rs.getString("product_type"));
				p.setPrice(rs.getInt("price"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public List<Product> selectProductId(int id) {
		String sql = "SELECT * FROM product WHERE product_type = ?";
	    List<Product> list = new ArrayList<>();
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Product p = new Product();
	            p.setId(rs.getInt("id"));
	            p.setProduct_id(rs.getString("product_id"));
	            p.setProduct_name(rs.getString("product_name"));
	            p.setProduct_type(rs.getString("product_type"));
	            p.setPrice(rs.getInt("price"));
	            list.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}


	@Override
	public Product findById(String productId) {
		
		String sql = "SELECT * FROM product WHERE product_id = ?";
		Product product = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_id(rs.getString("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_type(rs.getString("product_type"));
                product.setProduct_type1(rs.getString("product_type1"));
                product.setPrice(rs.getInt("price"));
                
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
	}

	
	//update
	
	
	
	//delete
	
	

}
