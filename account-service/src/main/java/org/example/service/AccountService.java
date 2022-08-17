package org.example.service;

import org.example.entity.Account;

import java.util.List;

public interface AccountService {
    Account getAccountById(Long accountId);
    Long createAccount(Account account);
    Account updateAccount(Long accountId, String name, String email, String phone, List<Long> bills);
    Account deleteAccount(Long accountId);
}
