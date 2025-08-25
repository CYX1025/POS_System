package dao;


import java.util.List;


import model.Porder_Detail;

public interface Porder_DetailDao {
	//create
	void add(Porder_Detail detail);
	void addList(List<Porder_Detail> details);
	
	//read
	List<Porder_Detail> selectAll();
	Porder_Detail SelectByOrder_No(String Order_No);
	List<Porder_Detail> AllOrder_No_Detail(String orderNo);
	//update
	void updateQuantityById(int id, int quantity);
	
	//delete
	void delete(String order_no);
	void deleteById(int id);

}
