package vn.com.gigo.services;

import vn.com.gigo.dtos.response.EmployeeDto;

public interface EmployeeService {
	Object getAllEmployee();

	Object getAllEmployeePagnation(int offSet, int pageSize);

	Object getEmployee(Long id);

	Object addEmployee(EmployeeDto employeeDto);

	void deleteEmployee(Long id);

	Object updateEmployee(Long id, EmployeeDto employeeDto);

	Object getEmployeeByStoreId(Long storeId);
	
	Object getEmployeeByUsername(String username);
}
