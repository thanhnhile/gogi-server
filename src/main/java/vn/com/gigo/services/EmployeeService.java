package vn.com.gigo.services;

import vn.com.gigo.dtos.EmployeeDto;

public interface EmployeeService {
	Object getAllEmployee();

	Object getAllEmployeePagnation(int offSet, int pageSize);

	Object getEmployee(Long id);

	Object addEmployee(EmployeeDto EmployeeDto);

	void deleteEmployee(Long id);

	Object updateEmployee(Long id, EmployeeDto EmployeeDto);

	Object getEmployeeByStoreId(Long storeId);
}
