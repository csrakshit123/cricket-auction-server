package com.cricket.server.mappers;

import com.cricket.server.model.auth.RegisterRequest;
import com.cricket.server.model.user.User;
import com.cricket.server.model.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);

    User registerRequestToUserEntity(RegisterRequest registerRequest);
}
