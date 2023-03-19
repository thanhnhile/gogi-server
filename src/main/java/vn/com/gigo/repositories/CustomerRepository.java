package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	@Query(value="SELECT * FROM customers WHERE account=?1",nativeQuery=true)
	List<Customer> findAllByAccount_Username(String username);
	
	@Query(value="SELECT * FROM customers WHERE account=?1 and is_default=1",nativeQuery=true)
	Customer getCustomerInfoDefaultByUsername(String username);
}
