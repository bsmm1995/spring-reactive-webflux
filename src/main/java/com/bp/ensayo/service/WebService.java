package com.bp.ensayo.service;

import com.bp.ensayo.service.dto.CategoryDTO;
import com.bp.ensayo.service.dto.ProductDTO;
import com.bp.ensayo.service.dto.UserDTO;

import java.util.List;

public interface WebService {
    List<ProductDTO> getProducts();

    List<UserDTO> getUsers();

    List<CategoryDTO> getCategories();
}
