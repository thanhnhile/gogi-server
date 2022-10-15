package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import vn.com.gigo.dtos.EmployeeDto;
import vn.com.gigo.entities.Employee;

@Mapper(componentModel="spring")
public interface EmployeeMapper {
	// ----------------------------Entity To DTO---------------------------

		EmployeeDto employeeToEmployeeDto(Employee employee);

		List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees);

		// ---------------------------DTO To Entity----------------------

		Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
