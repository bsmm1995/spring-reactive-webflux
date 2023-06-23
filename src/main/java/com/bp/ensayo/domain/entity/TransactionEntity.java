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
    @Column//(nullable = false, unique = true)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    //@Enumerated(EnumType.STRING)
    @Column//(name = "TRANSACTION_TYPE", nullable = false)
            TransactionType transactionType;

    @Column//(nullable = false)
    BigDecimal amount;

    //@ManyToOne
    // @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    AccountEntity account;
}
