package org.example.mapper;

import org.example.dto.AccountRequestDto;
import org.example.dto.AccountResponseDto;
import org.example.entity.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountRequestDtoMapper {

    Account toModel(AccountRequestDto requestDto);
}
