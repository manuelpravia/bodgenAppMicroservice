package com.mpraviap.sales_service.infraestructure.controller;

import com.mpraviap.sales_service.api.SaleApi;
import com.mpraviap.sales_service.application.service.SaleService;
import com.mpraviap.sales_service.infraestructure.mapper.SaleRequestControllerMapper;
import com.mpraviap.sales_service.infraestructure.mapper.SaleResponseControllerMapper;
import com.mpraviap.sales_service.model.SaleRequest;
import com.mpraviap.sales_service.model.SaleResponse;
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
public class SaleController implements SaleApi {

    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleRequestControllerMapper saleRequestControllerMapper;
    @Autowired
    private SaleResponseControllerMapper saleResponseControllerMapper;

    /**
     * POST /sale : Add new sales
     * Add a new sale to the database
     *
     * @param saleRequest structure sale creation (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid input (status code 405)
     */
    @Override
    public Mono<ResponseEntity<SaleResponse>> addSale(Mono<SaleRequest> saleRequest, ServerWebExchange exchange) {

        return saleRequest.map(saleRequestControllerMapper::toSaleRequestDto)
                .flatMap(saleService::saveSale)
                .map(saleResponseControllerMapper::toSaleResponse)
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .doFinally(user -> log.info("Fin de la operacion agregar proveedor"));
    }

    /**
     * DELETE /sale/{id} : Delete a Sale
     * Delete a sale
     *
     * @param id       Sale identifier (required)
     * @param exchange
     * @return Invalid parameter (status code 400)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteSale(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(saleService::deleteSale)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion eliminar proveedor "));
    }

    /**
     * GET /sale/{id} : Search for a sale by  their identifier.
     * Returned to the sale when it exists.
     *
     * @param id       sale identifier (required)
     * @param exchange
     * @return successful operation (status code 200)
     * or Invalid parameter (status code 400)
     * or Sale not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<SaleResponse>> getSaleById(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(saleService::getSaleById)
                .map(saleResponseControllerMapper::toSaleResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion consultar proveedor por ID"));
    }

    /**
     * GET /sale : Consult the list of sale
     * Return the list of sale.
     *
     * @param exchange
     * @return Successful operation (status code 200)
     * or Sale not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<SaleResponse>>> listSale(ServerWebExchange exchange) {

        return Mono.just(saleService.getSales().map(saleResponseControllerMapper::toSaleResponse))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .doFinally(user -> log.info("Fin de la operacion consultar la lista de proveedores"));
    }

    /**
     * PUT /sale/{id} : Update sale data
     * Update sale data using id.
     *
     * @param id          Sale identifier (required)
     * @param saleRequest Update an existent sale in the database (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid ID sale (status code 400)
     * or sale not found (status code 404)
     * or Validation exception (status code 405)
     */
    @Override
    public Mono<ResponseEntity<SaleResponse>> updateSale(String id, Mono<SaleRequest> saleRequest, ServerWebExchange exchange) {

        return saleRequest.map(saleRequestControllerMapper::toSaleRequestDto)
                .flatMap(providerRequestDto -> saleService.updateSale(providerRequestDto,id))
                .map(saleResponseControllerMapper::toSaleResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion actualizar proveedor"));
    }
}
