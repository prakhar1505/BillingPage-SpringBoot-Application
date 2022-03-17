package src.main.java.dao;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import src.main.java.entity.bill;

@Repository
public interface GanapatiDao extends JpaRepository<bill, Integer>{
	@Modifying
	@Transactional
	@Query("update bill b set b.item= ?1 , b.qty=?2 , b.price=?3 , b.value=?4, b.Name=?6 where b.id = ?5")
	void updateBillById(String item, float qty, float price, float value, int id,String Name);

	@Query("select b from bill b where b.date>=?1 and b.date<=?2")
	Optional<List<bill>> billListByDateRange(Date from, Date to);
	
	@Query("select SUM(b.value) from bill b where b.date=?1")
	float getStockBalance(Date date);
	
	@Query("select qty from stock s where s.item=?1")
	float getStock(String item);
	
	@Modifying
	@Transactional
	@Query("update stock s set s.qty= ?2 where s.item = ?1")
	void updateStock(String item, float qty);

}