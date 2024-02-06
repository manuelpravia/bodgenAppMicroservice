package com.mpraviap.product_service.infraestructure.controller;

import com.mpraviap.product_service.api.ProductApi;
import com.mpraviap.product_service.application.service.ProductService;
import com.mpraviap.product_service.infraestructure.mapper.ProductControllerMapper;
import com.mpraviap.product_service.model.ProductRequest;
import com.mpraviap.product_service.model.ProductResponse;
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
public class ProductController implements ProductApi {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductControllerMapper productControllerMapper;

    /**
     * POST /product : Add new product
     * Add a new product to the database
     *
     * @param productRequest structure product creation (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid input (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ProductResponse>> addProduct(Mono<ProductRequest> productRequest, ServerWebExchange exchange) {

        return productRequest.map(productControllerMapper::toProductRequestDto)
                .flatMap(productService::saveProduct)
                .map(productControllerMapper::toProductResponse)
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .doFinally(user -> log.info("Fin de la operacion agregar usuario"));
    }

    /**
     * DELETE /product/{id} : Delete a product
     * Delete a product
     *
     * @param id       Product identifier (required)
     * @param exchange
     * @return Invalid parameter (status code 400)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(productService::deleteProduct)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion eliminar usuario "));
    }

    /**
     * GET /product/{id} : Search for a product by  their identifier.
     * Returned to the product when it exists.
     *
     * @param id       Product identifier (required)
     * @param exchange
     * @return successful operation (status code 200)
     * or Invalid parameter (status code 400)
     * or Product not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<ProductResponse>> getProductById(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(productService::getProductById)
                .map(productControllerMapper::toProductResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion consultar usuario por ID"));
    }

    /**
     * GET /product : Consult the list of products
     * Return the list of products.
     *
     * @param exchange
     * @return Successful operation (status code 200)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<ProductResponse>>> listProducts(ServerWebExchange exchange) {

        return Mono.just(productService.getProducts().map(productControllerMapper::toProductResponse))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .doFinally(user -> log.info("Fin de la operacion consultar la lista de usuarios"));
    }

    /**
     * PUT /product/{id} : Update product data
     * Update product data using id.
     *
     * @param id             Product identifier (required)
     * @param productRequest Update an existent product in the database (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid ID user (status code 400)
     * or Product not found (status code 404)
     * or Validation exception (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ProductResponse>> updateProduct(String id, Mono<ProductRequest> productRequest, ServerWebExchange exchange) {

        return productRequest.map(productControllerMapper::toProductRequestDto)
                .flatMap(productRequestDto -> productService.updateProduct(productRequestDto,id))
                .map(productControllerMapper::toProductResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion actualizar usuario"));
    }
}
