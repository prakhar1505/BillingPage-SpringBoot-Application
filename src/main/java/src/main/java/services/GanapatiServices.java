package src.main.java.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.main.java.dao.GanapatiDao;
import src.main.java.dao.daoStock;
import src.main.java.entity.bill;
import src.main.java.entity.stock;

@Service
public class GanapatiServices implements IGanapatiService {

	@Autowired
	private GanapatiDao dao;

	@Autowired
	private daoStock dao1;
	
	public void saveBill(bill bill) {
		dao.save(bill);
	}
	
	public bill viewBill(int id)
	{
		Optional<bill> bill=dao.findById(id);
		return bill.get();
	}
	
	public void updateBill(bill bill)
	{
		dao.updateBillById(bill.getItem(), bill.getQty(), bill.getPrice(), bill.getValue(), bill.getId(), bill.getName());
	}
	public List<bill> billList(Date from, Date to)
	{
		return dao.billListByDateRange(from, to).get();
	}
	public float billBalance(Date date) {
		return dao.getStockBalance(date);
	}
	public void updateStock(String item, float qty) {
		float previousQty=dao.getStock(item);
		float newQty=previousQty+qty;
		dao.updateStock(item, newQty);
	}
	public List<stock> viewStock(){
		return dao1.viewStock().get();
	}
}
