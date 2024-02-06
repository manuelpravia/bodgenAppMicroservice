package com.mpraviap.user_service.domain.repository;

import com.mpraviap.user_service.domain.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    @Query("{ 'documentNumber' : ?0 }")
    Mono<User> findUserByDocumentNumber(String documentNumber);
}
