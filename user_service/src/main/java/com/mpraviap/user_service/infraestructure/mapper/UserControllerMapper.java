package com.mpraviap.user_service.infraestructure.mapper;

import com.mpraviap.user_service.application.dto.UserRequestDto;
import com.mpraviap.user_service.application.dto.UserResponseDto;
import com.mpraviap.user_service.model.UserRequest;
import com.mpraviap.user_service.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserControllerMapper {
    UserResponse toUserResponse(UserResponseDto userResponseDto);

    //@Mapping(target = "documentNumber", source = "customerRequest.documentNumber")
    UserRequestDto toUserRequestDto(UserRequest customerRequest);
}
