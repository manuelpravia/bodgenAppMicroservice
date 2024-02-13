/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpraviap.gateway_server.setups;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author manuelpravia
 */
@Slf4j
@Component
public class AuthenticationFiltering extends AbstractGatewayFilterFactory<AuthenticationFiltering.Config>{

    private static final String BEARER_PREFIX = "Bearer ";

    private final WebClient.Builder webclientBuilder;

      public AuthenticationFiltering(WebClient.Builder webclientBuilder) {
        super(Config.class);
        this.webclientBuilder = webclientBuilder;
    }
    
    
    @Override
    public GatewayFilter apply(Config config) {


        return new OrderedGatewayFilter((exchange, chain) -> {


             if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing  Authorization header");
             }
             
             
             String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                    if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Authorization structure");
                    }

                    String token = authHeader.substring(BEARER_PREFIX.length());
                    return  webclientBuilder.build()
                            .get()
                            //.uri("http://keycloack/roles").header(HttpHeaders.AUTHORIZATION, parts[1])
                            .uri("lb://security-service/authentication/valid")
                                .header(HttpHeaders.AUTHORIZATION,BEARER_PREFIX + token)
                            .retrieve()
                             .bodyToMono(JsonNode.class)
                             .map(response -> {  
                                 if(response != null){

                                   log.info("See Objects: " + response);  

                                   if(!response.get("is_valid").asBoolean()){
                                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Role Partners missing");
                                   }
                                 }else{
                                     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Roles missing");
                                 }
                             return exchange;
                     })
                       .onErrorMap(error -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Communication Error", error.getCause());})
                      .flatMap(chain::filter);                                                     
                },1);         
    }
    
    
    
 //Custom class to set parameter to globalFiltering
  public static class Config{
    
  }   
    
}
