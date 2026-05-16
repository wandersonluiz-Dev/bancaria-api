package com.bancaria.api.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(BigDecimal balance, BigDecimal amount) {
        super(String.format("saldo insuficiente. saldo atual R$ %.2f, valor solicitado: R$ %.2f", balance, amount));
    }
}
