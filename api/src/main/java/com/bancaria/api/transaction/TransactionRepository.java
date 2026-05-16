package com.bancaria.api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountIdOrTargetAccountIdOrderByCreatdDesc(Long accountId, Long targetAccountId);
}
