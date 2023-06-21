package com.bp.ensayo.ct1.service.mapper;

import com.bp.ensayo.ct1.service.dto.AccountDTO;
import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toEntity(AccountDTO dto);

    AccountDTO toDto(AccountEntity entity);
}
