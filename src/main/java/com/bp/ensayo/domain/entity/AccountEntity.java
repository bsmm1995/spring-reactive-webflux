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

@Data
@Table(name = "ACCOUNT")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity implements Serializable {
    @Id
    Long id;

    @Column(value = "ACCOUNT_NUMBER")
    String accountNumber;

    @Column
    BigDecimal amount;

    @Column
    AccountStatus status;

    @Column
    AccountType type;

    @Column(value = "CUSTOMER_ID")
    private Long customerId;
}
