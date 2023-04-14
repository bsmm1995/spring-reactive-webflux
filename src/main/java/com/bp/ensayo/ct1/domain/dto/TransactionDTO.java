package com.bp.ensayo.ct1.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDTO implements Serializable {
    @NotBlank
    String accountNumber;
    @NotNull
    @Min(1)
    BigDecimal amount;
}
