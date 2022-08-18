package example.mapper;

import example.dto.BillRequestDto;
import example.dto.BillResponseDto;
import example.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillResponseDtoMapper {
    public BillResponseDto toDto(Bill bill) {
        return BillResponseDto.builder()
                .accountId(bill.getAccountId())
                .creationDate(bill.getCreationDate())
                .amount(bill.getAmount())
                .isDefault(bill.getIsDefault())
                .overdraftEnabled(bill.getOverdraftEnabled())
                .build();
    }

    public Bill toModel(BillResponseDto billResponseDto) {
        return Bill.builder()
                .accountId(billResponseDto.getAccountId())
                .creationDate(billResponseDto.getCreationDate())
                .amount(billResponseDto.getAmount())
                .isDefault(billResponseDto.getIsDefault())
                .overdraftEnabled(billResponseDto.getOverdraftEnabled())
                .build();
    }
}
