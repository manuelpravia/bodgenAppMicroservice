package com.mpraviap.provider_service.application.service;


import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderService {
    Mono<ProviderResponseDto> getProviderById(String providerId);

    Flux<ProviderResponseDto> getProviders();

    Mono<ProviderResponseDto> saveProvider(ProviderRequestDto providerRequestDto);

    Mono<ProviderResponseDto> updateProvider(ProviderRequestDto providerRequestDto,String providerId);

    Mono<Void> deleteProvider(String providerId);
}
