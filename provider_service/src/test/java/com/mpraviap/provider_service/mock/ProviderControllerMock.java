package com.mpraviap.provider_service.mock;

import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.domain.model.Provider;
import com.mpraviap.provider_service.model.ProviderRequest;
import com.mpraviap.provider_service.utils.TestUtils;

import java.io.IOException;

public class ProviderControllerMock {

    private static  final String PATH = "mockController";

    public static ProviderRequest getProviderRequest(){
        try {
            ProviderRequest providerRequest = TestUtils
                    .readJsonFromFile(PATH,"ProviderRequest",ProviderRequest.class);
            return providerRequest;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
