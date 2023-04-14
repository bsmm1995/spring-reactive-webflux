package com.bp.ensayo.ct1.domain.dto;

import com.bp.ensayo.ct1.domain.enu.TransactionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDTO implements Serializable {
    Long id;
    BigDecimal amount;
    TransactionType transactionType;
}
