package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
	//create
	void addProduct(Product product);
	//read
	List<Product> selectAllProduct();
	List<Product> selectProductId(int id);
	Product findById(String productId);
	//update
	
	//delete

}
