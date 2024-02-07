package com.mpraviap.sales_service.application.service;

import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleService {
    Mono<SaleResponseDto> getSaleById(String saleId);

    Flux<SaleResponseDto> getSales();

    Mono<SaleResponseDto> saveSale(SaleRequestDto saleRequestDto);

    Mono<SaleResponseDto> updateSale(SaleRequestDto saleRequestDto,String saleId);

    Mono<Void> deleteSale(String providerId);
}
