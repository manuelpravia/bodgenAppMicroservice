package com.mpraviap.product_service.domain.repository;

import com.mpraviap.product_service.domain.model.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

    @Query("{ 'code' : ?0 }")
    Mono<Product> findProductByCode(String code);
}
