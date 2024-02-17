package com.mpraviap.provider_service.infraestructure.controller;

import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.application.service.impl.ProviderServiceImpl;
import com.mpraviap.provider_service.infraestructure.mapper.ProviderControllerMapperImpl;
import com.mpraviap.provider_service.mock.ProviderControllerMock;
import com.mpraviap.provider_service.mock.ProviderServiceMock;
import com.mpraviap.provider_service.model.ProviderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class ProviderControllerTest {

    @InjectMocks
    private ProviderController providerController;

    @Spy
    private ProviderControllerMapperImpl providerControllerMapper;

    @Mock
    private ProviderServiceImpl providerService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Returns a new provider when registration was successful")
    void returnsANewProviderWhenRegistrationWasSuccessful() {


        ProviderRequest providerRequest = ProviderControllerMock.getProviderRequest();
        ProviderResponseDto providerResponseDto = ProviderServiceMock.getProviderResponseDto();


        Mockito.when(providerService.saveProvider(any(ProviderRequestDto.class)))
                .thenReturn(Mono.just(providerResponseDto));

        StepVerifier.create(providerController.addProvider(Mono.just(providerRequest), null))
                .assertNext(xd -> xd.getBody().getEmail().equals(providerRequest.getEmail()))
                .verifyComplete();

    }

    @Test
    @DisplayName("Removes a provider when successful")
    void removesProviderWhenSuccessful() {

        String providerId = "63ab4d5b259e366938611208";
        Mockito.when(providerService.deleteProvider(anyString()))
                .thenReturn(Mono.when());

        StepVerifier.create(providerController.deleteProvider(providerId, null))
                .verifyComplete();
    }

    @Test
    @DisplayName("Returns a provider when the process finishes correctly")
    void returnsProviderWhenTheProcessFinishesCorrectly() {

        String providerId = "63ab4d5b259e366938611208";
        ProviderRequest providerRequest = ProviderControllerMock.getProviderRequest();
        ProviderResponseDto providerResponseDto = ProviderServiceMock.getProviderResponseDto();


        Mockito.when(providerService.getProviderById(anyString()))
                .thenReturn(Mono.just(providerResponseDto));

        StepVerifier.create(providerController.getProviderById(providerId, null))
                .assertNext(xd -> xd.getBody().getId().equals(providerId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Returns a provider list when the process finishes successfully")
    void returnsProviderListWhenTheProcessFinishesSuccessfully() {

        ProviderResponseDto providerResponseDto = ProviderServiceMock.getProviderResponseDto();


        Mockito.when(providerService.getProviders())
                .thenReturn(Flux.just(providerResponseDto));

        StepVerifier.create(providerController.listProvider(null))
                .assertNext( fluxResponseEntity -> HttpStatus.OK.equals(fluxResponseEntity.getStatusCode()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Returns an updated provider when the process completes successfully")
    void returnsAnUpdatedProviderWhenTheProcessCompletesSuccessfully() {

        String providerId = "63ab4d5b259e366938611208";
        ProviderRequest providerRequest = ProviderControllerMock.getProviderRequest();
        ProviderRequestDto providerRequestDto = ProviderServiceMock.getProviderRequestDto();
        ProviderResponseDto providerResponseDto = ProviderServiceMock.getProviderResponseDto();


        Mockito.when(providerService.updateProvider(providerRequestDto,providerId))
                .thenReturn(Mono.just(providerResponseDto));

        StepVerifier.create(providerController.updateProvider(providerId, Mono.just(providerRequest),null))
                .assertNext(xd -> xd.getBody().getId().equals(providerId))
                .verifyComplete();
    }
}