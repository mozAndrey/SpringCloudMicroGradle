package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;


@Builder
@Getter
@AllArgsConstructor
public class AccountResponseDto {

    private Long accountId;
    private String name;
    private String email;
    private String phone;
    private List<Long> bills;
    private OffsetDateTime creationDate;
}
