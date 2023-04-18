package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.gigo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findOneById(Long id);

	@Query(value = "SELECT * FROM products WHERE category_id = ?1", countQuery = "SELECT count(*) FROM products WHERE category_id = ?1", nativeQuery = true)
	Page<Product> getAllProductsByCategoryId(Long categoryId, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE products.name LIKE %:search%", countQuery = "SELECT count(*) FROM products WHERE name LIKE %:search%", nativeQuery = true)
	Page<Product> searchByName(String search, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE products.name LIKE %:search%", nativeQuery = true)
	List<Product> search(String search);

	
	@Query(value = "SELECT * FROM products WHERE id in (SELECT product_id FROM accounts_products WHERE account_id = ?1)", nativeQuery = true)
	List<Product> getAllProductsLiked(Long account_id);

	@EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "category", "accounts" })
	@Query(value = "SELECT * FROM products WHERE category_id = ?1", nativeQuery = true)
	List<Product> getProductsByCategoryId(Long categoryId);
}
