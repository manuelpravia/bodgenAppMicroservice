package com.mpraviap.shopping_service.application.service;

import com.mpraviap.shopping_service.application.dto.ShoppingRequestDto;
import com.mpraviap.shopping_service.application.dto.ShoppingResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShoppingService {

    Mono<ShoppingResponseDto> getShoppingById(String shoppingId);

    Flux<ShoppingResponseDto> getShopping();

    Mono<ShoppingResponseDto> saveShopping(ShoppingRequestDto shoppingRequestDto);

    Mono<ShoppingResponseDto> updateShopping(ShoppingRequestDto shoppingRequestDto,String shoppingId);

    Mono<Void> deleteShopping(String shoppingId);
}
