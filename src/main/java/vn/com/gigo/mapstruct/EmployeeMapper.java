package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.com.gigo.dtos.response.EmployeeDto;
import vn.com.gigo.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	// ----------------------------Entity To DTO---------------------------
	@Mapping(source = "account.username", target = "account")
	EmployeeDto employeeToEmployeeDto(Employee employee);

	List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees);

	// ---------------------------DTO To Entity----------------------

	@Mapping(source = "account", target = "account.username")
	Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
