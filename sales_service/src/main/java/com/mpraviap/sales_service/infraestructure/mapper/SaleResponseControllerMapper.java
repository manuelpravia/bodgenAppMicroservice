package com.mpraviap.sales_service.infraestructure.mapper;

import com.mpraviap.sales_service.application.dto.DetailResponseDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import com.mpraviap.sales_service.model.DetailResponse;
import com.mpraviap.sales_service.model.SaleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SaleResponseControllerMapper {

    DetailResponse toDetailResponseDto(DetailResponseDto detailResponseDto);

    SaleResponse toSaleResponse(SaleResponseDto saleResponseDto);
}
