package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.response.VoucherDto;
import vn.com.gigo.services.impl.VoucherServiceImpl;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {
	@Autowired
	private VoucherServiceImpl voucherImpl;
	
	@GetMapping()
	public DataResponse getAllVoucher() {
		return new DataResponse(voucherImpl.getAllVoucher());
	}
	
	@GetMapping("/{id}")
	public DataResponse getVoucherById(@PathVariable(value = "id") Long id) {
		return new DataResponse(voucherImpl.getVoucherById(id));
	}
	
	@GetMapping("/search")
	public DataResponse getVoucherByCode(@RequestParam(value = "code") String code) {
		return new DataResponse(voucherImpl.getVoucher(code));
	}
	
	@PostMapping()
	public DataResponse addVoucher(@RequestBody VoucherDto voucherDto) {
		return new DataResponse(voucherImpl.addVoucher(voucherDto));
	}
	
	@PutMapping("/{id}")
	public DataResponse updateVoucher(@PathVariable(value = "id") Long id, @RequestBody VoucherDto voucherDto) {
		return new DataResponse(voucherImpl.updateVoucher(id, voucherDto));
	}
	
	@DeleteMapping("/{id}")
	private void deleteVoucher(@PathVariable(value = "id") Long id) {
		voucherImpl.deleteVoucher(id);
	}
	
	@GetMapping("/account")
	private DataResponse getVoucherByAccount() {
		return new DataResponse(voucherImpl.getVoucherByAccount());
	}
}
