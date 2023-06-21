package com.bp.ensayo.service.mapper;

import com.bp.ensayo.service.dto.CustomerDTO;
import com.bp.ensayo.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toEntity(CustomerDTO dto);

    CustomerDTO toDto(CustomerEntity entity);
}
