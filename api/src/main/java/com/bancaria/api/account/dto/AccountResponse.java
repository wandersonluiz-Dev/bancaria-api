package com.bancaria.api.account.dto;

import com.bancaria.api.account.Account;
import com.bancaria.api.account.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(Long id,
                              String accountNumber,
                              BigDecimal balance,
                              AccountStatus status,
                              Long customerId,
                              LocalDateTime created
                               ) {


    public AccountResponse(Account account) {
        this(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus(),
                account.getCustomer().getId(),
                account.getCreated());
    }
}
