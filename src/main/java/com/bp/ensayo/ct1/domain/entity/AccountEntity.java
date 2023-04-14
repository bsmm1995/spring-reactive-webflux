package com.bp.ensayo.ct1.domain.entity;

import com.bp.ensayo.ct1.domain.enu.AccountStatus;
import com.bp.ensayo.ct1.domain.enu.AccountType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
}
