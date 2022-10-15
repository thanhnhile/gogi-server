package vn.com.gigo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findOneById(Long id);
	
	Optional<Account> findByUsername(String username);
}
