package vn.com.gigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
	District findOneById(Long id);
}
