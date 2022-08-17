package example.mapper;

import example.dto.BillResponseDto;
import example.entity.Bill;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BillResponseDtoMapper {
    BillResponseDto toDto(Bill bill);
    Bill toModel(BillResponseDto billResponseDto);
}
