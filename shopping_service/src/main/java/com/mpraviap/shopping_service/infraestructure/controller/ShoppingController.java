package com.mpraviap.shopping_service.infraestructure.controller;

import com.mpraviap.shopping_service.api.ShoppingApi;
import com.mpraviap.shopping_service.application.service.ShoppingService;
import com.mpraviap.shopping_service.infraestructure.mapper.ShoppingControllerMapper;
import com.mpraviap.shopping_service.model.ShoppingRequest;
import com.mpraviap.shopping_service.model.ShoppingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class ShoppingController implements ShoppingApi {

    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private ShoppingControllerMapper shoppingControllerMapper;

    /**
     * POST /shopping : Add new shopping
     * Add a new shopping to the database
     *
     * @param shoppingRequest structure shopping creation (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid input (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ShoppingResponse>> addShopping(Mono<ShoppingRequest> shoppingRequest, ServerWebExchange exchange) {

        return shoppingRequest.map(shoppingControllerMapper::toShoppingRequestDto)
                .flatMap(shoppingService::saveShopping)
                .map(shoppingControllerMapper::toShoppingResponse)
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .doFinally(user -> log.info("Fin de la operacion agregar usuario"));
    }

    /**
     * DELETE /shopping/{id} : Delete a Shopping
     * Delete a Shopping
     *
     * @param id       User identifier (required)
     * @param exchange
     * @return Invalid parameter (status code 400)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteShopping(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(shoppingService::deleteShopping)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion eliminar usuario "));
    }

    /**
     * GET /shopping/{id} : Search for a shopping by  their identifier.
     * Returned to the shopping when it exists.
     *
     * @param id       Shopping identifier (required)
     * @param exchange
     * @return successful operation (status code 200)
     * or Invalid parameter (status code 400)
     * or Shopping not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<ShoppingResponse>> getShoppingById(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(shoppingService::getShoppingById)
                .map(shoppingControllerMapper::toShoppingResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion consultar usuario por ID"));
    }

    /**
     * GET /shopping : Consult the list of shopping
     * Return the list of Shopping.
     *
     * @param exchange
     * @return Successful operation (status code 200)
     * or Shopping not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<ShoppingResponse>>> listShopping(ServerWebExchange exchange) {
        return Mono.just(shoppingService.getShopping().map(shoppingControllerMapper::toShoppingResponse))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .doFinally(user -> log.info("Fin de la operacion consultar la lista de usuarios"));
    }

    /**
     * PUT /shopping/{id} : Update shopping data
     * Update Shopping data using id.
     *
     * @param id              Shopping identifier (required)
     * @param shoppingRequest Update an existent shopping in the database (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid ID shopping (status code 400)
     * or Shopping not found (status code 404)
     * or Validation exception (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ShoppingResponse>> updateShopping(String id, Mono<ShoppingRequest> shoppingRequest, ServerWebExchange exchange) {

        return shoppingRequest.map(shoppingControllerMapper::toShoppingRequestDto)
                .flatMap(shoppingRequestDto -> shoppingService.updateShopping(shoppingRequestDto,id))
                .map(shoppingControllerMapper::toShoppingResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion actualizar usuario"));
    }
}
