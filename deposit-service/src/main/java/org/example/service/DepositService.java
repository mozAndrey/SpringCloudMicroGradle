package org.example.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.AccountResponseDto;
import org.example.dto.BillRequestDto;
import org.example.dto.BillResponseDto;
import org.example.dto.DepositResponseDto;
import org.example.entity.Deposit;
import org.example.exceptions.DepositServiceException;
import org.example.repository.DepositRepository;
import org.example.rest.AccountServiceClient;
import org.example.rest.BillServiceClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class DepositService {
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;
    private final AccountServiceClient accountServiceClient;
    private final BillServiceClient billServiceClient;
    private final RabbitTemplate rabbitTemplate;


    public DepositResponseDto deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account is null and Bill is null");
        }
        if (billId == null) {
            BillResponseDto billResponseDto = billServiceClient.getBillById(billId);
            BillRequestDto billRequestDto = createBillRequest(amount, billResponseDto);
            billServiceClient.updateBill(billId, billRequestDto);

            AccountResponseDto accountById = accountServiceClient.getAccountById(billResponseDto.getAccountId());
            depositRepository.save(Deposit.builder()
                    .amount(amount)
                    .billId(billId)
                    .creationDate(OffsetDateTime.now())
                    .email(accountById.getEmail())
                    .build());

            return createResponse(amount, accountById);
        }
        BillResponseDto defaultBill = getDefaultBill(accountId);
        BillRequestDto billRequestDto = createBillRequest(amount, defaultBill);
        billServiceClient.updateBill(defaultBill.getId(), billRequestDto);
        AccountResponseDto accountById = accountServiceClient.getAccountById(accountId);
        depositRepository.save(Deposit.builder()
                .amount(amount)
                .email(accountById.getEmail())
                .billId(defaultBill.getId())
                .creationDate(OffsetDateTime.now())
                .build());

        return createResponse(amount, accountById);

    }

    private DepositResponseDto createResponse(BigDecimal amount, AccountResponseDto accountById) {
        DepositResponseDto depositResponseDto = DepositResponseDto.builder()
                .email(accountById.getEmail())
                .amount(amount)
                .build();

        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, new ObjectMapper().writeValueAsString(depositResponseDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDto;
    }

    private BillRequestDto createBillRequest(BigDecimal amount, BillResponseDto billResponseDto) {
        return BillRequestDto.builder()
                .accountId(billResponseDto.getAccountId())
                .creationDate(billResponseDto.getCreationDate())
                .isDefault(billResponseDto.getIsDefault())
                .amount(billResponseDto.getAmount().add(amount))
                .overdraftEnabled(billResponseDto.getOverdraftEnabled())
                .build();
    }

    private BillResponseDto getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId)
                .stream()
                .filter(BillResponseDto::getIsDefault)
                .findFirst()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill for account: " + accountId));
    }
}
