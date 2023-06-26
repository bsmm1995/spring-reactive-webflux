package com.bp.ensayo.domain.entity;

import com.bp.ensayo.domain.enums.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Table(name = "TRANSACTION")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity implements Serializable {
    @Id
    Long id;

    @Column(value = "TRANSACTION_TYPE")
    TransactionType transactionType;

    BigDecimal amount;

    @Column(value = "ACCOUNT_ID")
    Long accountId;
}
