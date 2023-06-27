package vn.com.gigo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Query(value = "SELECT * FROM categories WHERE status = 1",nativeQuery=true)
	List<Category> getAvailableCategory();
}
