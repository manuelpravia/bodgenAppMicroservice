package com.mpraviap.shopping_service.application.service.impl;

import com.mpraviap.shopping_service.application.dto.ShoppingRequestDto;
import com.mpraviap.shopping_service.application.dto.ShoppingResponseDto;
import com.mpraviap.shopping_service.application.mapper.ShoppingServiceMapper;
import com.mpraviap.shopping_service.application.service.ShoppingService;
import com.mpraviap.shopping_service.domain.model.Shopping;
import com.mpraviap.shopping_service.domain.repository.ShoppingRepository;
import com.mpraviap.shopping_service.shared.exception.BusinessException;
import com.mpraviap.shopping_service.shared.exception.RequestException;
import com.mpraviap.shopping_service.shared.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;
    @Autowired
    private ShoppingServiceMapper shoppingServiceMapper;

    @Override
    public Mono<ShoppingResponseDto> getShoppingById(String shoppingId) {

        return Mono.just(shoppingId)
                .flatMap(shoppingRepository::findById)
                .filter(Objects::nonNull)
                .map(shoppingServiceMapper::toShoppingResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Flux<ShoppingResponseDto> getShopping() {

        return shoppingRepository.findAll()
                .filter(Objects::nonNull)
                .map(shoppingServiceMapper::toShoppingResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Mono<ShoppingResponseDto> saveShopping(ShoppingRequestDto shoppingRequestDto) {

        return Mono.just(shoppingRequestDto)
                .map(shoppingServiceMapper::toShopping)
                .flatMap(shoppingRepository::save)
                .map(shoppingServiceMapper::toShoppingResponseDto)
                .switchIfEmpty(Mono.error(new BusinessException("OB0006", HttpStatus.CONFLICT, Constant.OBJECT_EXIST)));
    }

    @Override
    public Mono<ShoppingResponseDto> updateShopping(ShoppingRequestDto shoppingRequestDto, String shoppingId) {

        return Mono.just(shoppingId)
                .flatMap(shoppingRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB009", Constant.OBJECT_NOT_FOUND)))
                .map(shopping -> shoppingServiceMapper.toShopping(shopping, shoppingRequestDto))
                .flatMap(shoppingRepository::save)
                .map(shoppingServiceMapper::toShoppingResponseDto);
    }

    @Override
    public Mono<Void> deleteShopping(String shoppingId) {

        return Mono.just(shoppingId)
                .flatMap(shoppingRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)))
                .map(Shopping::getId)
                .flatMap(shoppingRepository::deleteById);
    }
}
