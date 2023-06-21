package com.bp.ensayo.service.dto;

import com.bp.ensayo.domain.enums.AccountStatus;
import com.bp.ensayo.domain.enums.AccountType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO implements Serializable {
    Long id;
    @NotBlank
    String accountNumber;
    @NotNull
    @Min(0)
    BigDecimal amount;
    AccountStatus status;
    AccountType type;
    Customer customer;
}
