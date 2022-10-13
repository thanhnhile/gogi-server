package vn.com.gigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.gigo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
