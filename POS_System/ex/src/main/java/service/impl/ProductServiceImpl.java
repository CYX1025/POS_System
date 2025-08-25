package service.impl;

import java.util.List;

import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static ProductDaoImpl pddi = new ProductDaoImpl();
	
	
	@Override
	public List<Product> selectAllProduct() {
		return pddi.selectAllProduct();
	}
	
	
	

}
