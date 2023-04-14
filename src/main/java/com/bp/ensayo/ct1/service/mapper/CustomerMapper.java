package com.bp.ensayo.ct1.service.mapper;

import com.bp.ensayo.ct1.domain.dto.CustomerDTO;
import com.bp.ensayo.ct1.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toEntity(CustomerDTO dto);

    CustomerDTO toDto(CustomerEntity entity);
}
