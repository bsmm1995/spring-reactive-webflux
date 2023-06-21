package com.bp.ensayo.service.dto;

import com.bp.ensayo.domain.enums.IdentificationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO implements Serializable {
    Long id;
    @NotBlank
    String identification;
    @NotBlank
    String name;
    @NotBlank
    String lastname;
    IdentificationType identificationType;
    List<Account> accounts;
}
