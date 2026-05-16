package com.bancaria.api.account;

import com.bancaria.api.account.dto.AccountResponse;
import com.bancaria.api.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountResponse createAccount(Long custumerId) {

        var customer = customerRepository.findById(custumerId)
                .orElseThrow(() -> new IllegalArgumentException("cliente não encontrado "));

        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ATIVO);
        account.setAccountNumber(generateAccountNumber());

        return new AccountResponse(accountRepository.save(account));
    }

    private String generateAccountNumber() {
        return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
