package com.mpraviap.sales_service.application.mapper;

import com.mpraviap.sales_service.application.dto.DetailResponseDto;
import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import com.mpraviap.sales_service.application.dto.UserDto;
import com.mpraviap.sales_service.client.model.UserResponse;
import com.mpraviap.sales_service.domain.model.Sale;
import com.mpraviap.sales_service.domain.model.SaleDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {
                UserDto.class
        }
)
public interface SaleServiceMapper {

    Sale toSale(SaleRequestDto saleRequestDto);
    Sale toSale(@MappingTarget Sale sale, SaleRequestDto saleRequestDto);

    @Mapping(target = "user", expression = "java(new UserDto())")
    SaleResponseDto toSaleResponseDto(Sale sale);

    DetailResponseDto toDetailResponseDto(SaleDetail saleDetail);


    UserDto toUserDto(UserResponse userResponse);



}
