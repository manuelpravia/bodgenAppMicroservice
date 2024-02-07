package com.mpraviap.shopping_service.domain.repository;

import com.mpraviap.shopping_service.domain.model.Shopping;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ShoppingRepository extends ReactiveMongoRepository<Shopping,String> {
}
