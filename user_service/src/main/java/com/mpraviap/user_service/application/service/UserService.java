package com.mpraviap.user_service.application.service;

import com.mpraviap.user_service.application.dto.UserRequestDto;
import com.mpraviap.user_service.application.dto.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {

    Mono<UserResponseDto> getUserById(String idUser);

    Flux<UserResponseDto> getUsers();

    Mono<UserResponseDto> saveUser(UserRequestDto userRequestDto);

    Mono<UserResponseDto> updateUser(UserRequestDto userRequestDto,String userId);

    Mono<Void> deleteUser(String userId);
}
