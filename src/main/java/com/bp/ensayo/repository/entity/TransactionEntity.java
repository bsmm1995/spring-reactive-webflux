package com.bp.ensayo.repository.entity;

import com.bp.ensayo.repository.enums.TransactionType;
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

    BigDecimal amount;

    @Column(value = "TRANSACTION_TYPE")
    TransactionType transactionType;

    @Column(value = "ACCOUNT_ID")
    Long accountId;
}
