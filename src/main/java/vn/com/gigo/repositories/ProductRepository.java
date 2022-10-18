package vn.com.gigo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.gigo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value="SELECT * FROM products WHERE category_id = ?1",
			countQuery="SELECT count(*) FROM products WHERE category_id = ?1",nativeQuery=true)
	Page<Product> getAllProductsByCategoryId(Long categoryId,Pageable pageable);
}
