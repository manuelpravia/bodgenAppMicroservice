package com.mpraviap.user_service.application.service.impl;

import com.mpraviap.user_service.application.dto.UserRequestDto;
import com.mpraviap.user_service.application.dto.UserResponseDto;
import com.mpraviap.user_service.application.mapper.UserServiceMapper;
import com.mpraviap.user_service.application.service.UserService;
import com.mpraviap.user_service.domain.model.User;
import com.mpraviap.user_service.domain.repository.UserRepository;
import com.mpraviap.user_service.shared.exception.BusinessException;
import com.mpraviap.user_service.shared.exception.RequestException;
import com.mpraviap.user_service.shared.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public Mono<UserResponseDto> getUserById(String idUser) {

        return Mono.just(idUser)
                .flatMap(userRepository::findById)
                .filter(Objects::nonNull)
                .map(userServiceMapper::toUserResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Flux<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .filter(Objects::nonNull)
                .map(userServiceMapper::toUserResponseDto)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)));
    }

    @Override
    public Mono<UserResponseDto> saveUser(UserRequestDto userRequestDto) {

        return userRepository.findUserByDocumentNumber(userRequestDto.getDocumentNumber())
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .filter(Optional::isEmpty)
                .map(user -> userRequestDto)
                .map(userServiceMapper::toUser)
                .flatMap(userRepository::save)
                .map(userServiceMapper::toUserResponseDto)
                .switchIfEmpty(Mono.error(new BusinessException("OB0006", HttpStatus.CONFLICT, Constant.OBJECT_EXIST)));
    }

    @Override
    public Mono<UserResponseDto> updateUser(UserRequestDto userRequestDto, String userId) {
        return Mono.just(userId)
                .flatMap(userRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB009", Constant.OBJECT_NOT_FOUND)))
                .map(user -> userServiceMapper.toCustomer(user, userRequestDto))
                .flatMap(userRepository::save)
                .map(userServiceMapper::toUserResponseDto);
    }

    @Override
    public Mono<Void> deleteUser(String userId) {

        return Mono.just(userId)
                .flatMap(userRepository::findById)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RequestException("OB0004", Constant.OBJECT_NOT_FOUND)))
                .map(User::getId)
                .flatMap(userRepository::deleteById);
    }
}
