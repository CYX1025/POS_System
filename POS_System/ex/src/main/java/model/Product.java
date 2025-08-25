package model;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String product_id;	//產品代號
	private String product_name;	//產品名稱
	private String product_type;	//產品種類
	private String product_type1;
	private int price;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String product_id, String product_name, String product_type, String product_type1, int price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_type1 = product_type1;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getProduct_type1() {
		return product_type1;
	}

	public void setProduct_type1(String product_type1) {
		this.product_type1 = product_type1;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	

}
