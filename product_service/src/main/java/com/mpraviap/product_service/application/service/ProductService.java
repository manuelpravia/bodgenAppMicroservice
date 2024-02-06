package com.mpraviap.product_service.application.service;

import com.mpraviap.product_service.application.dto.ProductRequestDto;
import com.mpraviap.product_service.application.dto.ProductResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductResponseDto> getProductById(String idUser);

    Flux<ProductResponseDto> getProducts();

    Mono<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto);

    Mono<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto,String productId);

    Mono<Void> deleteProduct(String productId);
}
