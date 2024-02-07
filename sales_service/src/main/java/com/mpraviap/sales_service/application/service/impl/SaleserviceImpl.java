package com.mpraviap.sales_service.application.service.impl;

import com.mpraviap.sales_service.application.dto.DetailRequestDto;
import com.mpraviap.sales_service.application.dto.SaleRequestDto;
import com.mpraviap.sales_service.application.dto.SaleResponseDto;
import com.mpraviap.sales_service.application.mapper.SaleServiceMapper;
import com.mpraviap.sales_service.application.service.SaleService;
import com.mpraviap.sales_service.domain.model.Sale;
import com.mpraviap.sales_service.domain.repository.SaleRepository;
import com.mpraviap.sales_service.shared.exception.BusinessException;
import com.mpraviap.sales_service.shared.exception.RequestException;
import com.mpraviap.sales_service.shared.util.Enum.EnumUtil;
import com.mpraviap.sales_service.shared.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleserviceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleServiceMapper saleServiceMapper;

    @Override
    public Mono<SaleResponseDto> getSaleById(String saleId) {

        return Mono.just(saleId)
                .flatMap(saleRepository::findById)
                .filter(Objects::nonNull)
                .map(saleServiceMapper::toSaleResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Flux<SaleResponseDto> getSales() {

        return saleRepository.findAll()
                .filter(Objects::nonNull)
                .map(saleServiceMapper::toSaleResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Mono<SaleResponseDto> saveSale(SaleRequestDto saleRequestDto) {

        return saleRepository.findSaleBySaleCode(saleRequestDto.getSaleCode())
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .filter(Optional::isEmpty)
                .flatMap(sale -> this.calculateSaleAmount(saleRequestDto))
                .map(saleServiceMapper::toSale)
                .flatMap(saleRepository::save)
                .map(saleServiceMapper::toSaleResponseDto)
                .switchIfEmpty(Mono.error(new BusinessException("OB0006", HttpStatus.CONFLICT, Constant.OBJECT_EXIST)));
    }

    @Override
    public Mono<SaleResponseDto> updateSale(SaleRequestDto saleRequestDto, String saleId) {
        return null;
    }

    @Override
    public Mono<Void> deleteSale(String providerId) {

        return Mono.just(providerId)
                .flatMap(saleRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)))
                .map(Sale::getId)
                .flatMap(saleRepository::deleteById);
    }

    private Mono<SaleRequestDto> calculateSaleAmount(SaleRequestDto saleRequestDto){

        return Mono.just(saleRequestDto)
                .map(SaleRequestDto::getDetail)
                .map(details -> {
                    double grossPrice = details.stream()
                            .mapToDouble(DetailRequestDto::getSubTotal)
                            .sum();
                    double igvTotal = grossPrice*Constant.IGV;
                    double returnMoney = saleRequestDto.getAllocateAmount() - grossPrice - igvTotal;
                    saleRequestDto.setIgv(igvTotal);
                    saleRequestDto.setPriceFinal(grossPrice + igvTotal);
                    saleRequestDto.setRefundAmount(returnMoney);
                    saleRequestDto.setSaleStatus(EnumUtil.saleStatus.COMPLETE.toString());
                    return  saleRequestDto;
                });

    }
}