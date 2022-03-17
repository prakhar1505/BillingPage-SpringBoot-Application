package src.main.java.services;

import java.util.Date;
import java.util.List;

import src.main.java.entity.bill;
import src.main.java.entity.stock;

public interface IGanapatiService {
	
	public void saveBill(bill bill);
	
	public bill viewBill(int id);
	
	public void updateBill(bill bill);
	
	public List<bill> billList(Date from, Date to);

	public float billBalance(Date date);

	public void updateStock(String item, float qty);

	public List<stock> viewStock();

}