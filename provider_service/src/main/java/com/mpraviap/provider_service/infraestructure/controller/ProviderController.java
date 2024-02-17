package com.mpraviap.provider_service.infraestructure.controller;

import com.mpraviap.provider_service.api.ProviderApi;
import com.mpraviap.provider_service.application.service.ProviderService;
import com.mpraviap.provider_service.infraestructure.mapper.ProviderControllerMapper;
import com.mpraviap.provider_service.model.ProviderRequest;
import com.mpraviap.provider_service.model.ProviderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author manuelpravia
 */
@Slf4j
@RestController
public class ProviderController implements ProviderApi {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private ProviderControllerMapper providerControllerMapper;

    /**
     * POST /provider : Add new provider
     * Add a new provider to the database
     *
     * @param providerRequest structure provider creation (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid input (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ProviderResponse>>
        addProvider(Mono<ProviderRequest> providerRequest, ServerWebExchange exchange) {

        return providerRequest.map(providerControllerMapper::toProviderRequestDto)
                .flatMap(providerService::saveProvider)
                .map(providerControllerMapper::toProviderResponse)
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .doFinally(user -> log.info("Fin de la operacion agregar proveedor"));

    }

    /**
     * DELETE /provider/{id} : Delete a Provider
     * Delete a provider
     *
     * @param id       Provider identifier (required)
     * @param exchange
     * @return Invalid parameter (status code 400)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteProvider(String id, ServerWebExchange exchange) {


        return Mono.just(id)
                .flatMap(providerService::deleteProvider)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion eliminar proveedor "));
    }

    /**
     * GET /provider/{id} : Search for a provider by  their identifier.
     * Returned to the provider when it exists.
     *
     * @param id       Provider identifier (required)
     * @param exchange
     * @return successful operation (status code 200)
     * or Invalid parameter (status code 400)
     * or Provider not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<ProviderResponse>> getProviderById(String id, ServerWebExchange exchange) {


        return Mono.just(id)
                .flatMap(providerService::getProviderById)
                .map(providerControllerMapper::toProviderResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion consultar proveedor por ID"));
    }

    /**
     * GET /provider : Consult the list of providers
     * Return the llist of providers.
     *
     * @param exchange
     * @return Successful operation (status code 200)
     * or Providers not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<ProviderResponse>>> listProvider(ServerWebExchange exchange) {

        return Mono.just(providerService.getProviders().map(providerControllerMapper::toProviderResponse))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .doFinally(user -> log.info("Fin de la operacion consultar la lista de proveedores"));
    }

    /**
     * PUT /provider/{id} : Update provider data
     * Update provider data using id.
     *
     * @param id              Provider identifier (required)
     * @param providerRequest Update an existent Provider in the database (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid ID provider (status code 400)
     * or Provider not found (status code 404)
     * or Validation exception (status code 405)
     */
    @Override
    public Mono<ResponseEntity<ProviderResponse>>
        updateProvider(String id, Mono<ProviderRequest> providerRequest, ServerWebExchange exchange) {


        return providerRequest.map(providerControllerMapper::toProviderRequestDto)
                .flatMap(providerRequestDto -> providerService.updateProvider(providerRequestDto,id))
                .map(providerControllerMapper::toProviderResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion actualizar proveedor"));
    }
}
