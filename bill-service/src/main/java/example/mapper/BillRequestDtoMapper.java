package example.mapper;

import example.dto.BillRequestDto;
import example.entity.Bill;
import org.mapstruct.Mapper;

@Mapper
public interface BillRequestDtoMapper {
    BillRequestDto toDto(Bill bill);

    Bill toModel(BillRequestDto billRequestDto);
}
