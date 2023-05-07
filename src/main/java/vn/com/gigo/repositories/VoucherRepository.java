package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>{
	Voucher findOneById(Long id);
	
	@Query(value="SELECT * FROM vouchers WHERE end_date >= current_timestamp() AND id NOT IN (SELECT voucher_id FROM accounts_vouchers WHERE account_id = ?1) ORDER BY end_date  AND upper(code) = upper(?2) LIMIT 1", nativeQuery=true)
	Voucher findOneByCode(Long accountId, String code);

	@Query(value="SELECT * FROM vouchers WHERE end_date >= current_timestamp() AND id NOT IN (SELECT voucher_id FROM accounts_vouchers WHERE account_id = ?1) ORDER BY end_date", nativeQuery=true)
	List<Voucher> getVoucherByAccountId(Long accountId);
	
}
