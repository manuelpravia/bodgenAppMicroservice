package com.mpraviap.user_service.application.mapper;


import com.mpraviap.user_service.application.dto.UserRequestDto;
import com.mpraviap.user_service.application.dto.UserResponseDto;
import com.mpraviap.user_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserServiceMapper {

    User toUser(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(User user);

    User toCustomer(@MappingTarget User customer, UserRequestDto userRequestDto);

}
