package vn.com.gigo.services;

import vn.com.gigo.dtos.response.VoucherDto;

public interface VoucherService {
	Object getAllVoucher();
	
	Object addVoucher(VoucherDto voucherDto);

	void deleteVoucher(Long id);

	Object updateVoucher(Long id, VoucherDto voucherDto);
	
	Object getVoucherById(Long id);
	
	Object getVoucher(String code);

	Object getVoucherByAccount();
	
	Object addVoucherToAccount(String username, Long voucherId);
}
