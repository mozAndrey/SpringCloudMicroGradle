package org.example.service;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponseDto {

    private BigDecimal amount;
    private String email;
}
