package org.example.mapper;

import org.example.dto.AccountResponseDto;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseDtoMapper {
    public AccountResponseDto toDto(Account account) {
        return AccountResponseDto.builder()
                .accountId(account.getAccountId())
                .bills(account.getBills())
                .creationDate(account.getCreationDate())
                .email(account.getEmail())
                .name(account.getName())
                .phone(account.getPhone())
                .build();
    }
    public Account toModel(AccountResponseDto accountResponseDto) {
        return Account.builder()
                .accountId(accountResponseDto.getAccountId())
                .bills(accountResponseDto.getBills())
                .creationDate(accountResponseDto.getCreationDate())
                .email(accountResponseDto.getEmail())
                .name(accountResponseDto.getName())
                .phone(accountResponseDto.getPhone())
                .build();
    }
}
