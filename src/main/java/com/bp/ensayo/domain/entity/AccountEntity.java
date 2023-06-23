package com.bp.ensayo.domain.entity;

import com.bp.ensayo.domain.enums.AccountStatus;
import com.bp.ensayo.domain.enums.AccountType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name = "ACCOUNT")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity implements Serializable {
    @Id
    @Column
    // @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(value = "ACCOUNT_NUMBER")
    String accountNumber;

    @Column
    BigDecimal amount;

    //@Enumerated(EnumType.STRING)
    @Column
    AccountStatus status;

    // @Enumerated(EnumType.STRING)
    @Column
    AccountType type;

    //@ManyToOne
    // @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<TransactionEntity> transactions;
}
