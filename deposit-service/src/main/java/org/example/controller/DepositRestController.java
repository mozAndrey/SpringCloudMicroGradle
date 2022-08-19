package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DepositRequestDto;
import org.example.dto.DepositResponseDto;
import org.example.service.DepositService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepositRestController {

    private final DepositService depositService;


    @PostMapping("/deposits")
    public DepositResponseDto deposit(@RequestBody DepositRequestDto requestDto) {
        return depositService.deposit(requestDto.getAccountId(), requestDto.getBillId(), requestDto.getAmount());
    }
}
