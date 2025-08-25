package model;

import java.sql.Timestamp;

public class Porder {
	private String order_no;
	private Timestamp create_time;//建立時間
	public Porder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Porder(String order_no, Timestamp create_time) {
		super();
		this.order_no = order_no;
		this.create_time = create_time;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	};
	
	
}
