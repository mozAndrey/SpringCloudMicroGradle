package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountRequestDto;
import org.example.dto.AccountResponseDto;
import org.example.mapper.AccountRequestDtoMapper;
import org.example.mapper.AccountResponseDtoMapper;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountRequestDtoMapper accountRequestDtoMapper;
    private final AccountResponseDtoMapper accountResponseDtoMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountRequestDtoMapper accountRequestDtoMapper, AccountResponseDtoMapper accountResponseDtoMapper) {
        this.accountService = accountService;
        this.accountRequestDtoMapper = accountRequestDtoMapper;
        this.accountResponseDtoMapper = accountResponseDtoMapper;
    }

    @GetMapping("/{accountId}")
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        return accountResponseDtoMapper.toDto(accountService.getAccountById(accountId));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(accountRequestDtoMapper.toModel(accountRequestDto));
    }

    @PutMapping("/{accountId}")
    public AccountResponseDto updateAccount(@PathVariable Long accountId,
                                            @RequestBody AccountRequestDto accountRequestDto) {
        return accountResponseDtoMapper.toDto(accountService.updateAccount(accountId, accountRequestDto.getName(),
                accountRequestDto.getEmail(), accountRequestDto.getPhone(), accountRequestDto.getBills()));
    }

    @DeleteMapping("/{accountId}")
    public AccountResponseDto deleteAccount(@PathVariable Long accountId) {
        return accountResponseDtoMapper.toDto(accountService.deleteAccount(accountId));
    }
}
