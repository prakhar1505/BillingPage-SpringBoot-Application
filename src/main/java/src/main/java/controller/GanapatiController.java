package src.main.java.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.entity.bill;
import src.main.java.entity.stock;
import src.main.java.services.IGanapatiService;

@RestController
@RequestMapping(value="/GC")
@CrossOrigin
public class GanapatiController {
	@Autowired
	private IGanapatiService service;
	
	@PostMapping(value= "/saveBill")
	public ResponseEntity<String> saveBill(@RequestBody bill bill){
		service.saveBill(bill);
		service.updateStock(bill.getItem(), -bill.getQty());
		return new ResponseEntity<String>("Bill saved",HttpStatus.CREATED);
	}
	@GetMapping(value="/viewBill/{id}")
	public ResponseEntity<bill> viewBill(@PathVariable Integer id)
	{
		bill bill=service.viewBill(id);
		return new ResponseEntity<bill>(bill,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateBill/{id}")
	public ResponseEntity<String> updateBill(@RequestBody bill bill, @PathVariable Integer id){
		service.updateBill(bill);
		return new ResponseEntity<String>("Bill Updated",HttpStatus.CREATED);
	}
	
	@GetMapping(value="/billList/{from}/{to}")
	public ResponseEntity<List<bill>> billList(@PathVariable("from") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date from ,@PathVariable("to") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date to)
	{
		List<bill> bill=service.billList(from, to);
		return new ResponseEntity<List<bill>>(bill,HttpStatus.OK);
	}
	
	@GetMapping(value="/billBalance/{date}")
	public ResponseEntity<String> billbalance(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date)
	{
		float bill=service.billBalance(date);
		String s=Float.toString(bill);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping(value="/viewStock")
	public ResponseEntity<List<stock>> viewStock()
	{
		List<stock> stock=service.viewStock();
		return new ResponseEntity<List<stock>>(stock,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateStock/{item}/{qty}")
	public ResponseEntity<String> updateStock(@PathVariable String item, @PathVariable float qty){
		service.updateStock(item,qty);
		return new ResponseEntity<String>("Stock Updated",HttpStatus.CREATED);
	}
}