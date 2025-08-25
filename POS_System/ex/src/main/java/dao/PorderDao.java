package dao;

import java.util.List;

import model.Porder;

public interface PorderDao {
	
	//create
	
	void add(Porder porder);
	
	//read
	List<Porder> selectAll();
	Porder SelectByOrder_No(String Order_No);
	List<Porder>keyword(String keyword);
	//update
	
	//delete
	void delete(String order_no);
}
