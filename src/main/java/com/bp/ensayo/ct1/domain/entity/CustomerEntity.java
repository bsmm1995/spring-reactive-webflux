package com.bp.ensayo.ct1.domain.entity;

import com.bp.ensayo.ct1.domain.enu.IdentificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "CUSTOMER")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, unique = true)
    String identification;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "IDENTIFICATION_TYPE", nullable = false)
    IdentificationType identificationType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AccountEntity> accounts;
}
