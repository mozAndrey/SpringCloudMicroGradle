package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Account;
import org.example.exceptions.AccountNotFoundException;
import org.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account getAccountById(Long accountId) {
        return repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Unable to find account with id: " + accountId));
    }

    public Long createAccount(Account account) {
        return repository.save(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name, String email, String phone, List<Long> bills) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Unable to find account with id: " + accountId));
        account.setBills(bills);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        repository.save(account);
        return account;
    }

    public Account deleteAccount(Long accountId) {
        repository.deleteById(accountId);
        return getAccountById(accountId);
    }
}
