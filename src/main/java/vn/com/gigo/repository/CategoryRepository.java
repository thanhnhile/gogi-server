package vn.com.gigo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
