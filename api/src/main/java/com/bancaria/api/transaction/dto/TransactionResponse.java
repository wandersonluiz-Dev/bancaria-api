package com.bancaria.api.transaction.dto;

import com.bancaria.api.transaction.Transaction;
import com.bancaria.api.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        BigDecimal amount,
        Long sourceAccountId,
        String sourceAccountOwner,
        Long targetAccountId,
        BigDecimal balanceAfter,
        String description,
        LocalDateTime created) {


    public TransactionResponse(Transaction transaction) {
        this (
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getSourceAccount().getId(),
                transaction.getSourceAccount().getCustomer().getName(),
                transaction.getTargetAccount() != null ? transaction.getTargetAccount().getId() : null,
                transaction.getBalanceAfter(),
                transaction.getDescription(),
                transaction.getCreatd()
        );
    }
}
