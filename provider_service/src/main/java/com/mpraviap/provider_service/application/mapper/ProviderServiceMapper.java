package com.mpraviap.provider_service.application.mapper;

import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.domain.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProviderServiceMapper {
    Provider toProvider(ProviderRequestDto userRequestDto);

    ProviderResponseDto toProviderResponseDto(Provider provider);

    Provider toProvider(@MappingTarget Provider customer, ProviderRequestDto userRequestDto);
}
