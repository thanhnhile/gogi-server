package vn.com.gigo.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.request.AccountRoleDto;
import vn.com.gigo.dtos.response.RoleDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Role;
import vn.com.gigo.mapstruct.RoleMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.RoleRepository;
import vn.com.gigo.services.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepo) {
		this.roleMapper = roleMapper;
		this.roleRepo = roleRepo;
	}
	@Override
	public Object getAllRole() {
		return roleMapper.rolesToRoleDtos(roleRepo.findAll());
	}

	@Override
	public Object getAllRolePagnation(int offSet, int pageSize) {
		Page<Role> page = roleRepo.findAll(PageRequest.of(offSet, pageSize));
		return roleMapper.rolesToRoleDtos(page.getContent());
	}

	@Override
	public Object getRole(Integer id) {
		return roleMapper.roleToRoleDto(roleRepo.findOneById(id));
	}

	@Override
	public Object addRole(RoleDto roleDto) {
		Role role = roleRepo.save(roleMapper.roleDtoToRole(roleDto));
		return roleMapper.roleToRoleDto(roleRepo.save(role));
	}

	@Override
	public void deleteRole(Integer id) {
		roleRepo.delete(roleRepo.findOneById(id));
	}

	@Override
	public Object updateRole(Integer id, RoleDto roleDto) {
		Role RoleNew = roleMapper.roleDtoToRole(roleDto);
		Role RoleOld = roleRepo.findOneById(id);
		RoleOld.setName(RoleNew.getName());
		roleRepo.save(RoleOld);
		return roleMapper.roleToRoleDto(RoleOld);
	}

	@Override
	public Object addAccountToRole(AccountRoleDto accountRoleDto) {
		Role role = roleRepo.findOneById(accountRoleDto.getRole_id());
		Account account = accountRepo.findOneById(accountRoleDto.getAccount_id());
		
		role.getAccounts().add(account);
		account.getRoles().add(role);
		
		roleRepo.save(role);
		accountRepo.save(account);
		
		return accountRoleDto;
	}

}
