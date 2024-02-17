package com.mpraviap.provider_service.mock;

import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.domain.model.Provider;
import com.mpraviap.provider_service.utils.TestUtils;

import java.io.IOException;

public class ProviderServiceMock {

    private static  final String PATH = "mockService";


    public static Provider getProvider(){
        try {
            Provider provider = TestUtils.readJsonFromFile(PATH,"Provider",Provider.class);
            return provider;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProviderRequestDto getProviderRequestDto(){
        try {
            ProviderRequestDto providerRequestDto = TestUtils
                    .readJsonFromFile(PATH,"ProviderRequestDto",ProviderRequestDto.class);
            return providerRequestDto;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProviderResponseDto getProviderResponseDto(){
        try {
            ProviderResponseDto providerResponseDto = TestUtils
                    .readJsonFromFile(PATH,"ProviderResponseDto",ProviderResponseDto.class);
            return providerResponseDto;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
