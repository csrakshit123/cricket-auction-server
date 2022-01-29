package com.cricket.server.mappers;

import com.cricket.server.model.pool.Pool;
import com.cricket.server.model.pool.dto.PoolDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", uses = {PlayerPoolRowMapper.class})
public interface PoolMapper {

    PoolDto entityToDto(Pool pool);
    Pool dtoToEntity(PoolDto poolDto);

    List<PoolDto> entityToDtoList(List<Pool> poolList);
    List<Pool> dtoToEntityList(List<PoolDto> poolDtoList);
}
