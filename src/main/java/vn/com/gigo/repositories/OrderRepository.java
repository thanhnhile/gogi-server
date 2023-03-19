package vn.com.gigo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	
	@Query(value="SELECT * FROM orders WHERE store_id=?1 ORDER BY id desc",nativeQuery = true)
	List<Order> getOrdersByStoreId(Long storeId);
	
	List<Order> findByStore_IdAndStatus(Long storeId,int status);
	
	@Query(value="SELECT * FROM orders WHERE account = ?1 ORDER BY created_date desc",nativeQuery = true)
	List<Order> getOrdersByAccountUsername(String username);
	
	@Query(value="SELECT o.store_id,s.store_name,s.address,count(o.id) FROM ORDERS AS o,STORES AS s WHERE o.store_id = s.store_id AND (DATE(o.created_date) = CURDATE()) GROUP BY o.store_id",nativeQuery=true)
	List<Object[]> getCountOrderGroupByStore();
	
	@Query(value="SELECT sum(total) FROM ORDERS WHERE (DATE(created_date) = CURDATE()) AND pay=1",nativeQuery=true)
	Integer getSumOrderTotal();
	
	@Query(value="SELECT count(*) FROM ORDERS WHERE (DATE(created_date) = CURDATE())",nativeQuery=true)
	Integer getCountOrdersToday();
	
	@Query(value="SELECT count(*) FROM ORDERS WHERE status = ?1 AND (DATE(created_date) = CURDATE())",nativeQuery=true)
	Integer countByStatusEquals(int status);
	
	@Query(value="SELECT sum(total),count(id),DATE(created_date) FROM ORDERS WHERE pay=1 GROUP BY DATE(created_date) ORDER BY created_date DESC",nativeQuery=true)
	List<Object[]>getWeeklyRevenue();
	
}
