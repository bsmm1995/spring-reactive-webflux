package com.bp.ensayo.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO implements Serializable {
    Integer id;
    String email;
    String password;
    String name;
    String role;
    String avatar;
}