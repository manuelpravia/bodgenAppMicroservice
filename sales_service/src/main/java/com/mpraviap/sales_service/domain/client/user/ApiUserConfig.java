package com.mpraviap.sales_service.domain.client.user;

import com.mpraviap.sales_service.client.api.UserApi;
import com.mpraviap.sales_service.client.model.UserResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class ApiUserConfig {

    @Bean
    public UserApi userApi(){

        return new UserApi() {
            /**
             * GET /user/{userId} : Obtener un usuario por su ID
             *
             * @param userId   (required)
             * @param exchange
             * @return Usuario encontrado (status code 200)
             * or Usuario no encontrado (status code 404)
             */
            @Override
            public Mono<ResponseEntity<UserResponse>> getUserById(String userId, ServerWebExchange exchange) {

                WebClient webClient = WebClient.create("http://localhost:8086");

                return webClient.get()
                        .uri("/user/" + userId)
                        .retrieve()
                        .bodyToMono(UserResponse.class)
                        .map(userResponse -> {
                            // Modificar la respuesta si es necesario
                            userResponse.email(userResponse.getEmail().toUpperCase());
                            return ResponseEntity.ok(userResponse);
                        })
                        .onErrorResume(error -> {

                            // Manejar errores si la llamada a la otra API falla
                            return Mono.just(ResponseEntity.notFound().build());
                        });

            }


        };
    }
}
