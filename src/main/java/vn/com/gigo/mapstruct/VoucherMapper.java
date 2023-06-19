package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.com.gigo.dtos.response.VoucherDto;
import vn.com.gigo.entities.Voucher;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
	VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);

	// ----------------------------Entity To DTO---------------------------
	VoucherDto voucherToVoucherDto(Voucher voucher);

	List<VoucherDto> vouchersToVoucherDtos(List<Voucher> voucher);

	// ---------------------------DTO To Entity----------------------
	Voucher voucherDtoToVoucher(VoucherDto voucherDto);

	List<Voucher> voucherDtosToVouchers (List<VoucherDto> voucherDto);
}
