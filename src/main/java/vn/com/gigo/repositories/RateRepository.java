package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{
	@Query(value="SELECT product_id FROM rates,accounts WHERE rates.user_id = accounts.account_id AND username=?1",nativeQuery=true)
	List<Long> findProduct_IdByUser_Username(String username);
	
	@Query(value="SELECT * FROM rates WHERE rates.product_id=?1",nativeQuery=true)
	List<Rate> findByProductId(Long id);
}
