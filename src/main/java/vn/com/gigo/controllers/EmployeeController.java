package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.EmployeeDto;
import vn.com.gigo.services.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeImpl;

	@GetMapping()
	public DataResponse getAllEmployee() {
		return  new DataResponse(employeeImpl.getAllEmployee());
	}

	@GetMapping("/{offset}/{pageSize}")
	public DataResponse getAllEmployee(@PathVariable(value = "offset") int offSet,
			@PathVariable(value = "pageSize") int pageSize) {
		return new DataResponse(employeeImpl.getAllEmployeePagnation(offSet, pageSize));
	}

	@GetMapping("/{id}")
	public DataResponse getEmployee(@PathVariable(value = "id") Long id) {
		 return new DataResponse(employeeImpl.getEmployee(id));
	}

	@PostMapping()
	public DataResponse addEmployee(@RequestBody EmployeeDto employeeDto) {
		return new DataResponse(employeeImpl.addEmployee(employeeDto));
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long id) {
		 employeeImpl.deleteEmployee(id);
	}

	@PutMapping("/{id}")
	public DataResponse updateEmployee(@PathVariable(value = "id") Long id, @RequestBody EmployeeDto employeeDto) {
		return new DataResponse(employeeImpl.updateEmployee(id, employeeDto));
	}

	@GetMapping("/store/{store_id}")
	public DataResponse getEmployeeByProductId(@PathVariable(value = "store_id") Long storeId){
		return new DataResponse(employeeImpl.getEmployeeByStoreId(storeId));
	}
}
