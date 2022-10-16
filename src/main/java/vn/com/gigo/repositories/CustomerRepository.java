package vn.com.gigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
