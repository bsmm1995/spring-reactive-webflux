package com.bp.ensayo.ct1.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO implements Serializable {
    Integer id;
    String title;
    Double price;
    String description;
    private List<String> images;
    CategoryDTO category;
    Date creationAt;
    LocalDateTime updatedAt;
}
