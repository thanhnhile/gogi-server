package vn.com.gigo.services;

import vn.com.gigo.dtos.request.AccountRoleDto;
import vn.com.gigo.dtos.response.RoleDto;

public interface RoleService {
	Object getAllRole();

	Object getAllRolePagnation(int offSet, int pageSize);

	Object getRole(Integer id);

	Object addRole(RoleDto roleDto);

	void deleteRole(Integer id);

	Object updateRole(Integer id, RoleDto roleDto);
	
	Object addAccountToRole(AccountRoleDto accountRoleDto);
}
