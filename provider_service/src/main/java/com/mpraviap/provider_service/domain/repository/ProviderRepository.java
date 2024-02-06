package com.mpraviap.provider_service.domain.repository;

import com.mpraviap.provider_service.domain.model.Provider;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProviderRepository extends ReactiveMongoRepository<Provider,String> {

    @Query("{ 'documentNumber' : ?0 }")
    Mono<Provider> findProviderByDocumentNumber(String documentNumber);
}
