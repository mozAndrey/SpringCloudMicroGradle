package example.mapper;

import example.dto.BillResponseDto;
import example.entity.Bill;
import org.mapstruct.Mapper;

@Mapper
public interface BillResponseDtoMapper {
    BillResponseDto toDto(Bill bill);
    Bill toModel(BillResponseDto billResponseDto);
}
