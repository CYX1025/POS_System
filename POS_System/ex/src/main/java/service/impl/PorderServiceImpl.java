package service.impl;

import java.sql.Timestamp;

import java.util.List;


import dao.impl.PorderDaoImpl;
import dao.impl.Porder_DetailDaoImpl;
import model.Porder;
import model.Porder_Detail;
import service.PorderService;
import util.auto_numbering;


public class PorderServiceImpl implements PorderService{

	public static void main(String[] args) {
		
		/*
		List<Porder_Detail> list= new  PorderServiceImpl().findAllPorderDetail();
		for(Porder_Detail o:list)
		{
			System.out.println(o.getOrder_no()+o.getProduct_id()+o.getProduct_name()+"\n");
		}*/
		
	}
	
	private static PorderDaoImpl pdi = new PorderDaoImpl();
    private static Porder_DetailDaoImpl pddi = new Porder_DetailDaoImpl();
    
    
	@Override
	// 建立訂單（主檔 + 明細）
    public void createOrder(List<Porder_Detail> details) {
        // 1. 產生訂單編號
        String orderNo = auto_numbering.OrderNo();

        // 2. 建立 Porder 物件
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Porder order = new Porder(orderNo, now);

        // 3. 將訂單編號補到每個明細裡
        for (Porder_Detail detail : details) {
            detail.setOrder_no(orderNo);
        }

        // 4. 寫入主檔
        pdi.add(order);

        // 5. 寫入每一筆明細
        for (Porder_Detail detail : details) {
            pddi.add(detail);
        }

        System.out.println("訂單建立完成，訂單編號：" + orderNo);
    }


	@Override
	public List<Porder> findAllPorder() {
		return pdi.selectAll();
	}


	@Override
	public List<Porder_Detail> findAllPorderDetail() {
		return pddi.selectAll();
	}
	
	@Override
	public Porder_Detail checkPorderNo(String Order_No) {

		return pddi.SelectByOrder_No(Order_No);
	}
	
	
	@Override
	public List<Porder> keyword(String keyword) {
		return pdi.keyword(keyword);
	}
	
	@Override
	public List<Porder_Detail> findPorderDetailByOrderNo(String orderNo) {
		return pddi.AllOrder_No_Detail(orderNo);
	}
	

	@Override
	public void updatePorderDetailByQuantity(int detailId, int quantity) {
		new Porder_DetailDaoImpl().updateQuantityById(detailId, quantity);
		
	}



	@Override
	public void delete(String order_no) {
		new Porder_DetailDaoImpl().delete(order_no);
		new PorderDaoImpl().delete(order_no);
		
	}

	
	@Override
	public void deletePorderDetailItem(int detailId) {
		new Porder_DetailDaoImpl().deleteById(detailId);
		
	}


	


	


	

}
