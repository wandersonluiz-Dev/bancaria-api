package com.bancaria.api.exception;

public class AccountBlockedException extends RuntimeException {

    public AccountBlockedException(Long accountId) {
        super("Conta id" + accountId + "não está ativa e não pode realizar operações.");
    }
}
