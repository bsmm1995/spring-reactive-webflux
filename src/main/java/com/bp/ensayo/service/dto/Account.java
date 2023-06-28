package com.bp.ensayo.service.dto;

import com.bp.ensayo.repository.enums.AccountStatus;
import com.bp.ensayo.repository.enums.AccountType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {
    Long id;
    String accountNumber;
    BigDecimal amount;
    AccountStatus status;
    AccountType type;
    Long customerId;
}
