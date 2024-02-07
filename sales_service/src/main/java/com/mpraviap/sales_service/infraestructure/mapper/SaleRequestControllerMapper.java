package com.mpraviap.sales_service.infraestructure.mapper;

import com.mpraviap.sales_service.application.dto.DetailRequestDto;
import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.model.DetailRequest;
import com.mpraviap.sales_service.model.SaleRequest;
import com.mpraviap.sales_service.model.SaleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SaleRequestControllerMapper {

    DetailRequestDto toDetailRequestDto(DetailRequest detailRequestDto);

    SaleRequestDto toSaleRequestDto(SaleRequest saleRequestDto);
}
