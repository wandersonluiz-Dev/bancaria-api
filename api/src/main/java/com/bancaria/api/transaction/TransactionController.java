package com.bancaria.api.transaction;

import com.bancaria.api.transaction.dto.TransactionRequest;
import com.bancaria.api.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse execute(@RequestBody @Valid TransactionRequest request) {
        return service.execute(request);
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionResponse> getStatement(@PathVariable Long accountId) {
        return service.getStatementByAccountId(accountId);
    }
}
