package com.bp.ensayo.service.mapper;

import com.bp.ensayo.service.dto.AccountDTO;
import com.bp.ensayo.domain.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toEntity(AccountDTO dto);

    AccountDTO toDto(AccountEntity entity);
}
