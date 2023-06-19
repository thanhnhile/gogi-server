package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long>{
	Topping findOneById(Long id);
	
	@Query(value = "SELECT * FROM toppings WHERE id in (SELECT topping_id FROM orderDetails_toppings WHERE orderDetail_id = ?1)", nativeQuery = true)
	List<Topping> getToppingByOrderDetailId(Long orderDetail_id);
	
	@Query(value = "SELECT * FROM toppings WHERE status = 1",nativeQuery=true)
	List<Topping> getAvailableTopping();
}
