package com.bancaria.api.transaction.dto;

import com.bancaria.api.transaction.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @NotNull
        TransactionType type,
        @NotNull
        @Positive(message = "Valor inválido")
        BigDecimal amount,
        @NotNull
        Long sourceAccountId,

        Long targetAccountId,
        String description
) {
}
