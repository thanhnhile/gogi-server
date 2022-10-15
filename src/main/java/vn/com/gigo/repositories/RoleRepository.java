package vn.com.gigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.gigo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findOneById(Integer id);
}
