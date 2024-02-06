package com.mpraviap.product_service.shared.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

public class Utils {

    public static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime ldt){

        return Optional.ofNullable(ldt)
                .map(localDatetime -> localDatetime.atOffset(OffsetDateTime.now().getOffset()))
                .orElse(null);
    }

    public static LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime){

        return Optional.ofNullable(offsetDateTime)
                .map(OffsetDateTime::toLocalDateTime)
                .orElse(null);
    }

}
