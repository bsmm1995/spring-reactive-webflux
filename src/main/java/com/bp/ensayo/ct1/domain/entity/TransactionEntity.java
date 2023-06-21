package com.bp.ensayo.ct1.domain.entity;

import com.bp.ensayo.ct1.domain.enu.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "TRANSACTION")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE", nullable = false)
    TransactionType transactionType;

    @Column(nullable = false)
    BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    AccountEntity account;
}
