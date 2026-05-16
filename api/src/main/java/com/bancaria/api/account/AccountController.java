package com.bancaria.api.account;

import com.bancaria.api.account.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@PathVariable Long customerId) {
        return service.createAccount(customerId);
    }
}
