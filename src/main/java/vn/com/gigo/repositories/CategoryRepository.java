package vn.com.gigo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
