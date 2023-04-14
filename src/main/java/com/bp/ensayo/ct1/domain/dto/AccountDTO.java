package com.bp.ensayo.ct1.domain.dto;

import com.bp.ensayo.ct1.domain.enu.AccountStatus;
import com.bp.ensayo.ct1.domain.enu.AccountType;
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
public class AccountDTO implements Serializable {
    Long id;
    @NotBlank String accountNumber;
    @NotNull @Min(0) BigDecimal amount;
    AccountStatus status;
    AccountType type;
//    private CustomerDTO customer;
}
