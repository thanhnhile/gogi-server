package vn.com.gigo.controllers;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.AccountRoleDto;
import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.RoleDto;
import vn.com.gigo.services.impl.RoleServiceImpl;


@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleImpl;

	@GetMapping()
	@RolesAllowed({"ROLE_ADMIN"})
	public DataResponse getAllRole() {
		return new DataResponse( roleImpl.getAllRole());
	}

	@GetMapping("/{offSet}/{pageSize}")
	@RolesAllowed({"ROLE_ADMIN"})
	public DataResponse getAllRole(@PathVariable int offSet, @PathVariable int pageSize) {
		return new DataResponse( roleImpl.getAllRolePagnation(offSet, pageSize));
	}

	@GetMapping("/{id}")
	@RolesAllowed({"ROLE_ADMIN"})
	public DataResponse getCategory(@PathVariable(value = "id") Integer id) {
		return new DataResponse( roleImpl.getRole(id));
	}

	@PostMapping()
	@RolesAllowed({"ROLE_ADMIN"})
	public DataResponse addRole(@RequestBody RoleDto roleDto) {
		return new DataResponse(roleImpl.addRole(roleDto));
	}
	
	@PostMapping("/permission")
	@RolesAllowed({"ROLE_ADMIN"})
	public DataResponse addAccountToRole(@RequestBody AccountRoleDto accountRoleDto) {
		return new DataResponse(roleImpl.addAccountToRole(accountRoleDto));
	}

	@DeleteMapping("/{id}")
	@RolesAllowed({"ROLE_ADMIN"})
	public void deleteRole(@PathVariable(value = "id") Integer id) {
		roleImpl.deleteRole(id);
	}

	@PutMapping("/{id}")
	@RolesAllowed({"ROLE_ADMIN","ROLE_EDITOR"})
	public DataResponse updateRole(@PathVariable(value = "id") Integer id, @RequestBody RoleDto roleDto) {
		return new DataResponse(roleImpl.updateRole(id, roleDto));
	}
}
