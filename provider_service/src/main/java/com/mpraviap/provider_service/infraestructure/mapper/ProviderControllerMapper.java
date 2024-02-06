package com.mpraviap.provider_service.infraestructure.mapper;


import com.mpraviap.provider_service.application.dto.ProviderRequestDto;
import com.mpraviap.provider_service.application.dto.ProviderResponseDto;
import com.mpraviap.provider_service.model.ProviderRequest;
import com.mpraviap.provider_service.model.ProviderResponse;
import com.mpraviap.provider_service.shared.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {
                Utils.class
        }
)
public interface ProviderControllerMapper {

    //@Mapping(target = "createdAt", expression = "java(Utils.localDateTimeToOffsetDateTime(providerResponseDto.getCreatedAt()))")
    ProviderResponse toProviderResponse(ProviderResponseDto providerResponseDto);

    //@Mapping(target = "createdAt", expression = "java(Utils.offsetDateTimeToLocalDateTime(providerRequest.getCreatedAt()))")
    ProviderRequestDto toProviderRequestDto(ProviderRequest providerRequest);
}
