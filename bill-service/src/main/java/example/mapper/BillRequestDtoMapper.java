package example.mapper;

import example.dto.BillRequestDto;
import example.entity.Bill;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BillRequestDtoMapper {
    BillRequestDto toDto(Bill bill);

    Bill toModel(BillRequestDto billRequestDto);
}
