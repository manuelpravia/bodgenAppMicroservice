package com.mpraviap.product_service.application.service.impl;

import com.mpraviap.product_service.application.dto.ProductRequestDto;
import com.mpraviap.product_service.application.dto.ProductResponseDto;
import com.mpraviap.product_service.application.mapper.ProductServiceMapper;
import com.mpraviap.product_service.application.service.ProductService;
import com.mpraviap.product_service.domain.model.Product;
import com.mpraviap.product_service.domain.repository.ProductRepository;
import com.mpraviap.product_service.shared.exception.BusinessException;
import com.mpraviap.product_service.shared.exception.RequestException;
import com.mpraviap.product_service.shared.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceMapper productServiceMapper;

    @Override
    public Mono<ProductResponseDto> getProductById(String productId) {
        return Mono.just(productId)
                .flatMap(productRepository::findById)
                .filter(Objects::nonNull)
                .map(productServiceMapper::toProductResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Flux<ProductResponseDto> getProducts() {
        return productRepository.findAll()
                .filter(Objects::nonNull)
                .map(productServiceMapper::toProductResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));

    }

    @Override
    public Mono<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto) {

        return productRepository.findProductByCode(productRequestDto.getCode())
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .filter(Optional::isEmpty)
                .map(product -> productRequestDto)
                .map(productServiceMapper::toProduct)
                .map(product -> {
                    product.setRegistrationDate(LocalDateTime.now());
                    return product;
                })
                .flatMap(productRepository::save)
                .map(productServiceMapper::toProductResponseDto)
                .switchIfEmpty(Mono.error(new BusinessException("OB0006", HttpStatus.CONFLICT, Constant.OBJECT_EXIST)));

    }

    @Override
    public Mono<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, String productId) {

        return Mono.just(productId)
                .flatMap(productRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB009", Constant.OBJECT_NOT_FOUND)))
                .map(user -> productServiceMapper.toProduct(user, productRequestDto))
                .map(product -> {
                    product.setUpdateDate( LocalDateTime.now());
                    return product;
                })
                .flatMap(productRepository::save)
                .map(productServiceMapper::toProductResponseDto);

    }

    @Override
    public Mono<Void> deleteProduct(String productId) {
        return Mono.just(productId)
                .flatMap(productRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)))
                .map(Product::getId)
                .flatMap(productRepository::deleteById);

    }
}
