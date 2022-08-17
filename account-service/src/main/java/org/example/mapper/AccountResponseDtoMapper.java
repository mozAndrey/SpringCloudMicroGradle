package org.example.mapper;

import org.example.dto.AccountResponseDto;
import org.example.entity.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountResponseDtoMapper {
    AccountResponseDto toDto(Account account);
}
