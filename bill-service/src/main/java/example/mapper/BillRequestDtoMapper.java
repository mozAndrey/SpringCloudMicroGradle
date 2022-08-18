package example.mapper;

import example.dto.BillRequestDto;
import example.entity.Bill;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class BillRequestDtoMapper {
    public BillRequestDto toDto(Bill bill) {
        return BillRequestDto.builder()
                .accountId(bill.getAccountId())
                .creationDate(bill.getCreationDate())
                .amount(bill.getAmount())
                .isDefault(bill.getIsDefault())
                .overdraftEnabled(bill.getOverdraftEnabled())
                .build();
    }

    public Bill toModel(BillRequestDto billRequestDto) {
        return Bill.builder()
                .accountId(billRequestDto.getAccountId())
                .creationDate(OffsetDateTime.now())
                .amount(billRequestDto.getAmount())
                .isDefault(billRequestDto.getIsDefault())
                .overdraftEnabled(billRequestDto.getOverdraftEnabled())
                .build();
    }
}
