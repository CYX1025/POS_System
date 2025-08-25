package model;

import java.io.Serializable;

public class Porder_Detail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String order_no;	//訂單編號
	private String product_id;	//產品代號
	private String product_name;
	private Integer price;
	private Integer quantity;		//數量
	private String member;		//訂購人
	private String employee;	//店員點餐
	public Porder_Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Porder_Detail(String order_no, String product_id, String product_name, Integer price, Integer quantity,String member,String employee) {
		super();
		this.order_no = order_no;
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.member = member;
		this.employee = employee;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	
	
	
}
