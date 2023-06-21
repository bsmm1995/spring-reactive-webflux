package com.bp.ensayo.ct1.domain.dto;

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
public class TransferDTO implements Serializable {
    @NotBlank
    String accountNumberOrigin;
    @NotBlank
    String accountNumberDestination;
    @NotNull
    @Min(1)
    BigDecimal amount;
}
