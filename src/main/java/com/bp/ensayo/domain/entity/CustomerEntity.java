package com.bp.ensayo.domain.entity;

import com.bp.ensayo.domain.enums.IdentificationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "CUSTOMER")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity implements Serializable {
    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String identification;

    @Column
    String name;

    @Column
    String lastname;

    //@Enumerated(EnumType.STRING)
    @Column//name = "IDENTIFICATION_TYPE", nullable = false)
            IdentificationType identificationType;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AccountEntity> accounts;
}
