package com.mpraviap.sales_service.domain.repository;

import com.mpraviap.sales_service.domain.model.Sale;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SaleRepository extends ReactiveMongoRepository<Sale,String> {
    @Query("{ 'saleCode' : ?0 }")
    Mono<Sale> findSaleBySaleCode(String saleCode);
}
