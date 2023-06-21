package com.bp.ensayo.service.mapper;

import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.domain.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO toDto(TransactionEntity entity);
}
