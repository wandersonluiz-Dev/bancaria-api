package com.bancaria.api.exception;

public class AccountNOtFoundException extends RuntimeException {

    public AccountNOtFoundException(Long id) {
        super("Conta não encontrada" + id);
    }
}
