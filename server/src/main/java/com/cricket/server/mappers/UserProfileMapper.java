package com.cricket.server.mappers;

import com.cricket.server.model.user.UserProfile;
import com.cricket.server.model.user.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile dtoToEntity(UserProfileDto userProfileDto);
    UserProfileDto entityToDto(UserProfile userProfile);

    List<UserProfile> dtoToEntityList(List<UserProfileDto> userProfileDtos);
    List<UserProfileDto> entityToDtoList(List<UserProfile> userProfiles);
}
