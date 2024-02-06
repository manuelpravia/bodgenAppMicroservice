package com.mpraviap.product_service.application.mapper;


import com.mpraviap.product_service.application.dto.ProductRequestDto;
import com.mpraviap.product_service.application.dto.ProductResponseDto;
import com.mpraviap.product_service.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductServiceMapper {

    Product toProduct(ProductRequestDto productRequestDto);

    ProductResponseDto toProductResponseDto(Product user);

    Product toProduct(@MappingTarget Product product, ProductRequestDto productRequestDto);

}
