package example.controller;

import example.dto.BillRequestDto;
import example.dto.BillResponseDto;
import example.entity.Bill;
import example.mapper.BillRequestDtoMapper;
import example.mapper.BillResponseDtoMapper;
import example.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BillRestController {

    private final BillService billService;
    private final BillResponseDtoMapper billResponseDtoMapper;
    private final BillRequestDtoMapper billRequestDtoMapper;

    @GetMapping("/{billId}")
    public BillResponseDto getBill(@PathVariable Long billId) {
        return billResponseDtoMapper.toDto(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.createBill(billRequestDtoMapper.toModel(billRequestDto));
    }

    @PutMapping("/{billId}")
    public BillResponseDto updateBill(@PathVariable Long billId,
                                      @RequestBody BillRequestDto billRequestDto) {
        return billResponseDtoMapper.toDto(billService.updateBill(billId, billRequestDto.getAccountId(), billRequestDto.getAmount(),
                billRequestDto.getIsDefault(), billRequestDto.getOverdraftEnabled()));
    }

    @DeleteMapping("/{billId}")
    public BillResponseDto deleteBill(@PathVariable Long billId) {
        return billResponseDtoMapper.toDto(billService.deleteBill(billId));
    }
}
