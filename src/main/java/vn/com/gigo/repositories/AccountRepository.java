package vn.com.gigo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findOneById(Long id);
	
	Account findOneByUsername(String username);
	
	Optional<Account> findByUsername(String username);
	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account findByEmail(String email);
	
	@Query("SELECT a FROM Account a WHERE a.token = ?1")
	Account findByToken(String token);
	
	@Query(value="SELECT product_id FROM accounts_products WHERE account_id = ?1", nativeQuery = true)
	List<Long> findAllProductIdByAccountId(Long accountId);
}
