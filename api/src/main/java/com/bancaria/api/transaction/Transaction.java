package com.bancaria.api.transaction;

import com.bancaria.api.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "source-account-id", nullable = false)
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target-account-id")
    private Account targetAccount;

    @Column(nullable = false)
private BigDecimal balanceAfter;

    private String description;

    private LocalDateTime creatd;

    @PrePersist
    public void prePersist()
    {
        this.creatd = LocalDateTime.now();
    }
}
