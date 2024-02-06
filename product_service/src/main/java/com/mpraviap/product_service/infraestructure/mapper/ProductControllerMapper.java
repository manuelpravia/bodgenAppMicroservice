package com.mpraviap.product_service.infraestructure.mapper;

import com.mpraviap.product_service.application.dto.ProductRequestDto;
import com.mpraviap.product_service.application.dto.ProductResponseDto;
import com.mpraviap.product_service.model.ProductRequest;
import com.mpraviap.product_service.model.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductControllerMapper {

    ProductResponse toProductResponse(ProductResponseDto productResponseDto);

    ProductRequestDto toProductRequestDto(ProductRequest productRequest);
}
