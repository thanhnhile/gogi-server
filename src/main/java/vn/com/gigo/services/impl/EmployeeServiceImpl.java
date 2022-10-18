package vn.com.gigo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.EmployeeDto;
import vn.com.gigo.dtos.PagingDto;
import vn.com.gigo.entities.Employee;
import vn.com.gigo.mapstruct.EmployeeMapper;
import vn.com.gigo.repositories.EmployeeRepository;
import vn.com.gigo.services.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepo) {
		this.employeeMapper = employeeMapper;
		this.employeeRepo = employeeRepo;
	}
	@Override
	public Object getAllEmployee() {
		return employeeMapper.employeesToEmployeeDtos(employeeRepo.findAll());
	}

	@Override
	public Object getAllEmployeePagnation(int offSet, int pageSize) {
		Pageable pageable = PageRequest.of(offSet - 1, pageSize);
		Page<Employee> page = employeeRepo.findAll(pageable);
		PagingDto response = new PagingDto();
		response.setCurrentPage(offSet);
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		List<EmployeeDto> listEmployeeDtos = employeeMapper.employeesToEmployeeDtos(page.getContent());
		response.setContent(listEmployeeDtos);
		return page;
	}

	@Override
	public Object getEmployee(Long id) {
		return employeeMapper.employeeToEmployeeDto(employeeRepo.findOneById(id));
	}

	@Override
	public Object addEmployee(EmployeeDto EmployeeDto) {
		return employeeRepo.save(employeeMapper.employeeDtoToEmployee(EmployeeDto));
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepo.delete(employeeRepo.findOneById(id));
	}

	@Override
	public Object updateEmployee(Long id, EmployeeDto employeeDto) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if(employee.isPresent()) {
			employeeDto.setId(id);
			Employee update = employeeMapper.employeeDtoToEmployee(employeeDto);
			return employeeMapper.employeeToEmployeeDto(update);
		}
		else 
			return null;
	}
	@Override
	public Object getEmployeeByStoreId(Long storeId) {
		return employeeMapper.employeesToEmployeeDtos(employeeRepo.findByStoreId(storeId));
	}
}