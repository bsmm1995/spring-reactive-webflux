package com.bp.ensayo.domain.entity;

import com.bp.ensayo.domain.enums.AccountStatus;
import com.bp.ensayo.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "ACCOUNT")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    String accountNumber;

    @Column(nullable = false)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountType type;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<TransactionEntity> transactions;
}
