package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	Store findOneById(Long id);
	
	@Query("SELECT s FROM Store s, District d WHERE s.district.id = d.id AND d.id = :districtId")
	List<Store> findByDistrictId(Long districtId);
}
