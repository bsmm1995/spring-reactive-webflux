package com.bp.ensayo.service.dto;

import com.bp.ensayo.repository.enums.IdentificationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

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
}
