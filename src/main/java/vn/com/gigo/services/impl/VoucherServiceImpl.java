package vn.com.gigo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.VoucherDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Voucher;
import vn.com.gigo.mapstruct.VoucherMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.VoucherRepository;
import vn.com.gigo.security.SecurityUtils;
import vn.com.gigo.services.VoucherService;

@Service
@Transactional
public class VoucherServiceImpl implements VoucherService{
	
	@Autowired
	private VoucherMapper voucherMapper;
	
	@Autowired
	private VoucherRepository voucherRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	public VoucherServiceImpl(VoucherMapper voucherMapper, VoucherRepository voucherRepo) {
		this.voucherMapper = voucherMapper;
		this.voucherRepo = voucherRepo;
	}
	
	@Override
	public Object getAllVoucher() {
		return voucherMapper.vouchersToVoucherDtos(voucherRepo.findAll());
	}

	@Override
	public Object addVoucher(VoucherDto voucherDto) {
		Voucher voucher = voucherRepo.save(voucherMapper.voucherDtoToVoucher(voucherDto));
		return voucherMapper.voucherToVoucherDto(voucher);
	}

	@Override
	public void deleteVoucher(Long id) {
		voucherRepo.delete(voucherRepo.findOneById(id));
	}

	@Override
	public Object updateVoucher(Long id, VoucherDto voucherDto) {
		Optional<Voucher> voucher = voucherRepo.findById(id);
		if(voucher.isPresent()) {
			voucherDto.setId(id);
			Voucher voucherToUpdate = voucherMapper.voucherDtoToVoucher(voucherDto);
			return voucherMapper.voucherToVoucherDto(voucherRepo.save(voucherToUpdate));
		}
		else
			return null;
	}

	@Override
	public Object getVoucherById(Long id) {
		return voucherMapper.voucherToVoucherDto(voucherRepo.findOneById(id));
	}

	@Override
	public Object getVoucher(String code) {
		return voucherMapper.voucherToVoucherDto(voucherRepo.findOneByCode(code));
	}

	@Override
	public Object getVoucherByAccount() {
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		List<Voucher> listVoucher = voucherRepo.getVoucherByAccountId(account.getId());
		return voucherMapper.vouchersToVoucherDtos(listVoucher);
	}

}
