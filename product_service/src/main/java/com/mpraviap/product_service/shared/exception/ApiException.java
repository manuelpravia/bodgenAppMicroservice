package com.mpraviap.product_service.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(value = RequestException.class)
    public Mono<ResponseEntity<ErrorDto>> requestExceptionHandler(RequestException ex){
        return Mono.just(ex)
                .map(exception ->{
                    ErrorDto errorDto = ErrorDto.builder()
                            .code(ex.getCode())
                            .message(ex.getMessage())
                            .build();
                    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
                });
    }

    @ExceptionHandler(value = BusinessException.class)
    public Mono<ResponseEntity<ErrorDto>> businessExceptionHandler(BusinessException ex){
        return Mono.just(ex)
                .map(exception ->{
                    ErrorDto errorDto = ErrorDto.builder()
                            .code(ex.getCode())
                            .message(ex.getMessage())
                            .build();
                    return new ResponseEntity<>(errorDto, ex.getStatus());
                });
    }

}
