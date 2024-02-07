package com.mpraviap.sales_service.application.mapper;

import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import com.mpraviap.sales_service.domain.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SaleServiceMapper {

    Sale toSale(SaleRequestDto saleRequestDto);

    SaleResponseDto toSaleResponseDto(Sale sale);

}
