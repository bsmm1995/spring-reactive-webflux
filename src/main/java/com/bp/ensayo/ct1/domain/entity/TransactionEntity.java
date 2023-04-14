package com.bp.ensayo.ct1.domain.entity;

import com.bp.ensayo.ct1.domain.enu.TransactionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "TRANSACTION")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    AccountEntity account;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE", nullable = false)
    TransactionType transactionType;

    @Column(nullable = false)
    BigDecimal amount;
}
