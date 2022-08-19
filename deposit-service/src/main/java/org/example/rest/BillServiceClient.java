package org.example.rest;

import org.example.dto.BillRequestDto;
import org.example.dto.BillResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {

    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.GET)
    BillResponseDto getBillById(@PathVariable Long billId);

    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
    void updateBill(@PathVariable Long billId, BillRequestDto billRequestDto);

    @RequestMapping(value = "/bills/account/{accountId}", method = RequestMethod.GET)
    List<BillResponseDto> getBillsByAccountId(@PathVariable Long accountId);
}
