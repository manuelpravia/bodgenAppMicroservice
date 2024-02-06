package com.mpraviap.provider_service.application.service.impl;

import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.application.mapper.ProviderServiceMapper;
import com.mpraviap.provider_service.application.service.ProviderService;
import com.mpraviap.provider_service.domain.model.Provider;
import com.mpraviap.provider_service.domain.repository.ProviderRepository;
import com.mpraviap.provider_service.shared.exception.BusinessException;
import com.mpraviap.provider_service.shared.exception.RequestException;
import com.mpraviap.provider_service.shared.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderServiceMapper providerServiceMapper;
    @Override
    public Mono<ProviderResponseDto> getProviderById(String providerId) {

        return Mono.just(providerId)
                .flatMap(providerRepository::findById)
                .filter(Objects::nonNull)
                .map(providerServiceMapper::toProviderResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Flux<ProviderResponseDto> getProviders() {
        return providerRepository.findAll()
                .filter(Objects::nonNull)
                .map(providerServiceMapper::toProviderResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Mono<ProviderResponseDto> saveProvider(ProviderRequestDto providerRequestDto) {
        return providerRepository.findProviderByDocumentNumber(providerRequestDto.getDocumentNumber())
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .filter(Optional::isEmpty)
                .map(provider -> providerRequestDto)
                .map(providerServiceMapper::toProvider)
                .flatMap(providerRepository::save)
                .map(providerServiceMapper::toProviderResponseDto)
                .switchIfEmpty(Mono.error(new BusinessException("OB0006", HttpStatus.CONFLICT, Constant.OBJECT_EXIST)));
    }

    @Override
    public Mono<ProviderResponseDto> updateProvider(ProviderRequestDto providerRequestDto, String providerId) {
        return Mono.just(providerId)
                .flatMap(providerRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB009", Constant.OBJECT_NOT_FOUND)))
                .map(provider -> providerServiceMapper.toProvider(provider, providerRequestDto))
                .flatMap(providerRepository::save)
                .map(providerServiceMapper::toProviderResponseDto);
    }

    @Override
    public Mono<Void> deleteProvider(String providerId) {

        return Mono.just(providerId)
                .flatMap(providerRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)))
                .map(Provider::getId)
                .flatMap(providerRepository::deleteById);
    }
}
