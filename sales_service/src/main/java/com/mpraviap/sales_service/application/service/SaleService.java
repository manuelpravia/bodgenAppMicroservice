package com.mpraviap.sales_service.application.service;

import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleService {
    Mono<SaleResponseDto> getSaleById(String saleId, ServerWebExchange exchange);

    Flux<SaleResponseDto> getSales(ServerWebExchange exchange);

    Mono<SaleResponseDto> saveSale(SaleRequestDto saleRequestDto, ServerWebExchange exchange);

    Mono<SaleResponseDto> updateSale(SaleRequestDto saleRequestDto,String saleId, ServerWebExchange exchange);

    Mono<Void> deleteSale(String providerId);
}
