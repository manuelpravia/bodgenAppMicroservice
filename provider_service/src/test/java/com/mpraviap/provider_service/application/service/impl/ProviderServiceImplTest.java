package com.mpraviap.provider_service.application.service.impl;

import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.mapper.ProviderServiceMapperImpl;
import com.mpraviap.provider_service.domain.model.Provider;
import com.mpraviap.provider_service.domain.repository.ProviderRepository;
import com.mpraviap.provider_service.mock.ProviderServiceMock;
import com.mpraviap.provider_service.shared.exception.RequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class ProviderServiceImplTest {

    @Mock
    ProviderRepository providerRepository;

    @Spy
    ProviderServiceMapperImpl providerServiceMapper;

    @InjectMocks
    ProviderServiceImpl providerService;


    @Test
    @DisplayName("Returns Provider when its identifier is sent")
    void returnsProviderWhenItsIdentifierIsSent() {
        String providerId = "63ab4d5b259e366938611208";
        Provider provider = ProviderServiceMock.getProvider();

        Mockito.when(providerRepository.findById(anyString()))
                .thenReturn(Mono.just(provider));

        StepVerifier.create(providerService.getProviderById(providerId))
                .assertNext(providerResponseDto -> providerResponseDto.getId().equals(providerId))
                .verifyComplete();

    }

    @Test
    @DisplayName("Returns the list of providers when they exist")
    void returnsTheListOfProvidersWhenTheyExist() {

        Provider provider = ProviderServiceMock.getProvider();

        Mockito.when(providerRepository.findAll())
                .thenReturn(Flux.just(provider));

        StepVerifier.create(providerService.getProviders())
                .assertNext(providerResponseDto -> providerResponseDto.getId().equals(provider.getId()))
                .verifyComplete();
    }

    @Test
    @DisplayName("returns customer when it has been registered in the database")
    void returnsProviderWhenItHasBeenRegisteredInTheDatabase() {

        Provider provider = ProviderServiceMock.getProvider();
        ProviderRequestDto providerRequestDto = ProviderServiceMock.getProviderRequestDto();

        Mockito.when(providerRepository.save(any(Provider.class)))
                .thenReturn(Mono.just(provider));
        Mockito.when(providerRepository.findProviderByDocumentNumber(anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(providerService.saveProvider(providerRequestDto))
                .assertNext(providerResponseDto -> providerResponseDto.getDocumentNumber()
                        .equals(provider.getDocumentNumber()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Returns a provider when the data is updated")
    void returnsProviderWhenTheDataIsUpdated() {

        Provider provider = ProviderServiceMock.getProvider();
        ProviderRequestDto providerRequestDto = ProviderServiceMock.getProviderRequestDto();

        Mockito.when(providerRepository.save(any(Provider.class)))
                .thenReturn(Mono.just(provider));
        Mockito.when(providerRepository.findById(anyString()))
                .thenReturn(Mono.just(provider));

        StepVerifier.create(providerService.updateProvider(providerRequestDto,anyString()))
                .assertNext(providerResponseDto -> providerResponseDto.getDocumentNumber()
                        .equals(provider.getDocumentNumber()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Deletes a supplier when we pass it its identifier")
    void deletesProviderWhenWePassItItsIdentifier() {

        String providerId = "63ab4d5b259e366938611208";
        Provider provider = ProviderServiceMock.getProvider();

        Mockito.when(providerRepository.deleteById(anyString()))
                .thenReturn(Mono.when());
        Mockito.when(providerRepository.findById(anyString()))
                .thenReturn(Mono.just(provider));

        StepVerifier.create(providerService.deleteProvider(providerId))
                .verifyComplete();
    }

    @Test
    @DisplayName("returns error when provider does not exist")
    void returnsErrorWhenProviderDoesNotExist() {
        String providerId = "63ab4d5b259e366938611208";

        Mockito.when(providerRepository.findById(anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(providerService.deleteProvider(providerId))
                .expectError(RequestException.class)
                .verify();

    }
}