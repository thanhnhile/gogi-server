package vn.com.gigo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.response.VoucherDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Voucher;
import vn.com.gigo.exception.ResourceNotFoundException;
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
		Voucher voucherToAdd = voucherMapper.voucherDtoToVoucher(voucherDto);
//		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		isoFormat.setTimeZone(TimeZone.getTimeZone("GMT-7:00"));
//		try {
//			Date startDate = isoFormat.parse(voucherDto.getStartDate().toString());
//			Date endDate = isoFormat.parse(voucherDto.getEndDate().toString());
//			voucherToAdd.setStartDate(startDate);
//			voucherToAdd.setEndDate(endDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return voucherMapper.voucherToVoucherDto(voucherRepo.save(voucherToAdd));
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
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		return voucherMapper.voucherToVoucherDto(voucherRepo.findOneByCode(account.getId(), code));
	}

	@Override
	public Object getVoucherByAccount() {
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		List<Voucher> listVoucher = voucherRepo.getVoucherByAccountId(account.getId());
		return voucherMapper.vouchersToVoucherDtos(listVoucher);
	}

	@Override
	public Object addVoucherToAccount(String username, Long voucherId) {
		Account account = accountRepo.findOneByUsername(username);
		Voucher voucher = voucherRepo.findOneById(voucherId);
		if(account != null && voucher != null) {
			account.getVouchers().add(voucher);
			return "Added";
		}
		else throw new ResourceNotFoundException("Account or Voucher does not exist");
	}

}
