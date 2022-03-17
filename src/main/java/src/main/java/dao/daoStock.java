package src.main.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import src.main.java.entity.stock;

@Repository
public interface daoStock extends JpaRepository<stock, Integer>{
	@Query("select b from stock b")
	Optional<List<stock>> viewStock();
	
}