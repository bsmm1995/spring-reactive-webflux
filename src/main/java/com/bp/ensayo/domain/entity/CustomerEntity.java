package com.bp.ensayo.domain.entity;

import com.bp.ensayo.domain.enums.IdentificationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table(name = "CUSTOMER")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity implements Serializable {
    @Id
    Long id;

    @Column
    String identification;

    @Column
    String name;

    @Column
    String lastname;

    @Column(value = "IDENTIFICATION_TYPE")
    IdentificationType identificationType;
}
