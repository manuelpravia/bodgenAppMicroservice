package com.mpraviap.user_service.infraestructure.controller;

import com.mpraviap.user_service.api.UserApi;
import com.mpraviap.user_service.application.service.UserService;
import com.mpraviap.user_service.infraestructure.mapper.UserControllerMapper;
import com.mpraviap.user_service.model.UserRequest;
import com.mpraviap.user_service.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserControllerMapper userControllerMapper;

    /**
     * POST /user : Add new user
     * Add a new user to the database
     *
     * @param userRequest structure user creation (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid input (status code 405)
     */
    @Override
    public Mono<ResponseEntity<UserResponse>> addUser( Mono<UserRequest> userRequest, ServerWebExchange exchange) {

        return userRequest.map(userControllerMapper::toUserRequestDto)
                .flatMap(userService::saveUser)
                .map(userControllerMapper::toUserResponse)
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .doFinally(user -> log.info("Fin de la operacion agregar usuario"));
    }

    /**
     * DELETE /user/{id} : Delete a user
     * Delete a user
     *
     * @param id       User identifier (required)
     * @param exchange
     * @return Invalid parameter (status code 400)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteUser(String id, ServerWebExchange exchange) {
        return Mono.just(id)
                .flatMap(userService::deleteUser)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion eliminar usuario "));
    }

    /**
     * GET /user/{id} : Search for a user by  their identifier.
     * Returned to the user when it exists.
     *
     * @param id       User identifier (required)
     * @param exchange
     * @return successful operation (status code 200)
     * or Invalid parameter (status code 400)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {

        return Mono.just(id)
                .flatMap(userService::getUserById)
                .map(userControllerMapper::toUserResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion consultar usuario por ID"));
    }

    /**
     * GET /user : Consult the list of users
     * Return the llist of users.
     *
     * @param exchange
     * @return Successful operation (status code 200)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<UserResponse>>> listUsers(ServerWebExchange exchange) {
        return Mono.just(userService.getUsers().map(userControllerMapper::toUserResponse))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .doFinally(user -> log.info("Fin de la operacion consultar la lista de usuarios"));
    }

    /**
     * PUT /user/{id} : Update user data
     * Update user data using id.
     *
     * @param id          User identifier (required)
     * @param userRequest Update an existent user in the database (required)
     * @param exchange
     * @return Successful operation (status code 200)
     * or Invalid ID user (status code 400)
     * or User not found (status code 404)
     * or Validation exception (status code 405)
     */
    @Override
    public Mono<ResponseEntity<UserResponse>> updateUser(String id, Mono<UserRequest> userRequest, ServerWebExchange exchange) {
        return userRequest.map(userControllerMapper::toUserRequestDto)
                .flatMap(userRequestDto -> userService.updateUser(userRequestDto,id))
                .map(userControllerMapper::toUserResponse)
                .map(ResponseEntity::ok)
                .doFinally(user -> log.info("Fin de la operacion actualizar usuario"));
    }

}
