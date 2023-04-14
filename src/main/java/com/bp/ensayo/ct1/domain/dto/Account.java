package com.bp.ensayo.ct1.domain.dto;

import com.bp.ensayo.ct1.domain.enu.AccountStatus;
import com.bp.ensayo.ct1.domain.enu.AccountType;
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
}
