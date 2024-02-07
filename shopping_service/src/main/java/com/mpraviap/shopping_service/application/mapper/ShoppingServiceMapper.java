package com.mpraviap.shopping_service.application.mapper;

import com.mpraviap.shopping_service.application.dto.DetailDto;
import com.mpraviap.shopping_service.application.dto.ShoppingRequestDto;
import com.mpraviap.shopping_service.application.dto.ShoppingResponseDto;
import com.mpraviap.shopping_service.application.dto.UserDto;
import com.mpraviap.shopping_service.domain.model.Detail;
import com.mpraviap.shopping_service.domain.model.Shopping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ShoppingServiceMapper {
    Shopping toShopping(ShoppingRequestDto shoppingRequestDto);

    ShoppingResponseDto toShoppingResponseDto(Shopping shopping);

    Shopping toShopping(@MappingTarget Shopping shopping, ShoppingRequestDto shoppingRequestDto);

    DetailDto toDetailDto(Detail detail);
    Detail toDetail(DetailDto detailDto);
}
