package com.mpraviap.shopping_service.infraestructure.mapper;

import com.mpraviap.shopping_service.application.dto.DetailDto;
import com.mpraviap.shopping_service.application.dto.ShoppingRequestDto;
import com.mpraviap.shopping_service.application.dto.ShoppingResponseDto;
import com.mpraviap.shopping_service.application.dto.UserDto;
import com.mpraviap.shopping_service.model.Detail;
import com.mpraviap.shopping_service.model.ShoppingRequest;
import com.mpraviap.shopping_service.model.ShoppingResponse;
import com.mpraviap.shopping_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ShoppingControllerMapper {

    ShoppingRequestDto toShoppingRequestDto(ShoppingRequest shoppingRequest);

    ShoppingResponse toShoppingResponse(ShoppingResponseDto shoppingResponseDto);

    DetailDto toDetailDto(Detail detail);

    Detail toDetail(DetailDto detailDto);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

}
