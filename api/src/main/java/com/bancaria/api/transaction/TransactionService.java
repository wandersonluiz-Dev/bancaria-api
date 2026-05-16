package com.bancaria.api.transaction;


import com.bancaria.api.account.Account;
import com.bancaria.api.account.AccountRepository;
import com.bancaria.api.account.AccountStatus;
import com.bancaria.api.exception.AccountBlockedException;
import com.bancaria.api.exception.AccountNOtFoundException;
import com.bancaria.api.exception.InsufficientBalanceException;
import com.bancaria.api.transaction.dto.TransactionRequest;
import com.bancaria.api.transaction.dto.TransactionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public TransactionResponse execute(TransactionRequest request) {
        return switch (request.type()) {
            case DEPOSITO -> deposit(request);
            case SAQUE -> withdraw(request);
            case TRANSFERENCIA -> transfer(request);
        };

    }

    private TransactionResponse deposit(TransactionRequest request) {
        var account = findActiveAccount(request.sourceAccountId());

        account.setBalance(account.getBalance().add(request.amount()));
        accountRepository.save(account);

        return saveTransaction(request, account, null, account.getBalance());

    }

    private  TransactionResponse withdraw(TransactionRequest request) {
        var account = findActiveAccount(request.sourceAccountId());

        if (account.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientBalanceException(account.getBalance(), request.amount());
        }

        BigDecimal newBalance = account.getBalance().subtract(request.amount());
        account.setBalance(newBalance);
        accountRepository.save(account);

        return saveTransaction(request, account, null, account.getBalance());
    }

    private TransactionResponse transfer(TransactionRequest request) {
        if (request.targetAccountId() == null) {
            throw new IllegalArgumentException("Conta de destino é obrigatório para transferências");
        }
        if (request.sourceAccountId().equals(request.targetAccountId())) {
            throw new IllegalArgumentException("Conta de origem e destino não podem ser a mesma");
        }

        Long firstId = Math.min(request.sourceAccountId(), request.targetAccountId());
        Long secondId = Math.max(request.sourceAccountId(), request.targetAccountId());

        var first = findActiveAccount(firstId);
        var second = findActiveAccount(secondId);

        var source = first.getId().equals(request.sourceAccountId()) ? first : second;
        var target = first.getId().equals(request.targetAccountId()) ? first : second;

        if (source.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientBalanceException(source.getBalance(), request.amount());
        }

        BigDecimal newSourceBalance = source.getBalance().subtract(request.amount());
        source.setBalance(newSourceBalance);
        target.setBalance(target.getBalance().add(request.amount()));
        accountRepository.save(source);
        accountRepository.save(target);

        return saveTransaction(request, source, target, source.getBalance());
    }

    public List<TransactionResponse> getStatementByAccountId(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new AccountNOtFoundException(accountId);
        }
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountIdOrderByCreatdDesc(accountId, accountId)
                .stream()
                .map(TransactionResponse::new)
                .toList();
    }

private Account findActiveAccount(Long id) {
        var account = accountRepository.findById(id).orElseThrow(() -> new AccountNOtFoundException(id));

        if (account.getStatus() != AccountStatus.ATIVO) {
            throw new AccountBlockedException(id);
        }
        return account;
}

private TransactionResponse saveTransaction(TransactionRequest request,
                                            Account source,
                                            Account target,
                                            java.math.BigDecimal balanceAfter) {

        var transaction = new Transaction();
        transaction.setType(request.type());
        transaction.setAmount(request.amount());
        transaction.setSourceAccount(source);
        transaction.setTargetAccount(target);
        transaction.setBalanceAfter(balanceAfter);
        transaction.setDescription(request.description());

        return new TransactionResponse(transactionRepository.save(transaction));
    }
}
