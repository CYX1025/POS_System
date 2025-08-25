package service;

import java.util.List;

import model.Porder;
import model.Porder_Detail;

public interface PorderService {
	
	//create
	void createOrder(List<Porder_Detail> details);
	
	//read
	List<Porder> findAllPorder();
	List<Porder_Detail> findAllPorderDetail();
	Porder_Detail checkPorderNo(String Order_No);	//查詢訂單使用
	List<Porder> keyword(String keyword);
	List<Porder_Detail> findPorderDetailByOrderNo(String orderNo);
	
	
	//update
	void updatePorderDetailByQuantity(int detailId, int quantity);
	
	//delete
	void delete(String order_no);
	void deletePorderDetailItem(int detailId);

}
