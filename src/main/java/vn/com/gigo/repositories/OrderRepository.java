package vn.com.gigo.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	
	@Query(value="SELECT * FROM orders WHERE store_id=?1 ORDER BY id desc",
	countQuery="SELECT count(*) FROM orders WHERE store_id=?1",nativeQuery = true)
	Page<Order> getOrdersByStoreId(Long storeId,Pageable pageable);
	
	@Query(value="SELECT * FROM orders WHERE account = ?1 ORDER BY created_date desc",nativeQuery = true)
	List<Order> getOrdersByAccountUsername(String username);
	
}
