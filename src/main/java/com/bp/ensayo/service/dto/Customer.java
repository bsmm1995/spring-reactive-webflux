package com.bp.ensayo.service.dto;

import com.bp.ensayo.domain.enums.IdentificationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer implements Serializable {
    Long id;
    String identification;
    String name;
    String lastname;
    IdentificationType identificationType;
}
