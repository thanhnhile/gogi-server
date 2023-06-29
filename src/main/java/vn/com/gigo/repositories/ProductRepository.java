package vn.com.gigo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.gigo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findOneById(Long id);

	@Query(value = "SELECT * FROM products WHERE category_id = ?1 AND status = 1", countQuery = "SELECT count(*) FROM products WHERE category_id = ?1 AND status = 1", nativeQuery = true)
	Page<Product> getAllProductsByCategoryId(Long categoryId, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE products.name LIKE %:search% AND status = 1", countQuery = "SELECT count(*) FROM products WHERE name LIKE %:search% AND status = 1", nativeQuery = true)
	Page<Product> searchByName(String search, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE products.name LIKE %:search% AND status = 1", nativeQuery = true)
	List<Product> search(String search);

	@Query(value = "SELECT * FROM products WHERE id in (SELECT product_id FROM accounts_products WHERE account_id = ?1) AND status = 1", nativeQuery = true)
	List<Product> getAllProductsLiked(Long account_id);

	@Query(value = "SELECT * FROM products WHERE category_id = ?1 AND status = 1", nativeQuery = true)
	List<Product> getProductsByCategoryId(Long categoryId);
	
	@Query(value="SELECT * FROM products WHERE id in (SELECT t.product_id FROM (SELECT product_id FROM order_details GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 6) AS t) AND status = 1", nativeQuery=true)
	List<Product> getBestSeller();
	
	@Query(value="SELECT * FROM products WHERE id in (SELECT t.product_id FROM (SELECT product_id FROM order_details, orders WHERE order_details.order_id = orders.id AND orders.account = ?1 GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 6) AS t) AND status = 1", nativeQuery=true)
	List<Product> getProductsForYou(String username);
	
	@Query(value="SELECT * FROM products WHERE products.name LIKE '%combo%' AND status = 1",
			nativeQuery=true)
	List<Product> getCombo();
	
	@Query(value="SELECT * FROM products WHERE status = 1", nativeQuery=true)
	List<Product> getAvailableProduct();
}
