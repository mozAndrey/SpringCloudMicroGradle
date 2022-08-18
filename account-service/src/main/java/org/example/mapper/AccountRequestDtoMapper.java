package org.example.mapper;

import org.example.dto.AccountRequestDto;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestDtoMapper {

    public AccountRequestDto toDto(Account account) {
        return AccountRequestDto.builder()
                .accountId(account.getAccountId())
                .bills(account.getBills())
                .creationDate(account.getCreationDate())
                .email(account.getEmail())
                .name(account.getName())
                .phone(account.getPhone())
                .build();
    }

    public Account toModel(AccountRequestDto accountRequestDto) {
        return Account.builder()
                .accountId(accountRequestDto.getAccountId())
                .bills(accountRequestDto.getBills())
                .creationDate(accountRequestDto.getCreationDate())
                .email(accountRequestDto.getEmail())
                .name(accountRequestDto.getName())
                .phone(accountRequestDto.getPhone())
                .build();
    }
}
