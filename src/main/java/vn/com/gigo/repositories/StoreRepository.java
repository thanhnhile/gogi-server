package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	Store findOneById(Long id);
	
	@Query(value="SELECT * FROM stores WHERE province_id = ?1 AND district_id = ?2",nativeQuery=true)
	List<Store> findByAddress(Long provinceId,Long districtId);
	
	List<Store> findOneByProvinceId(Long provinceId);
}
