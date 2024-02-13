/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpraviap.keycloackAdapter.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manuelpravia
 */
@Service
public class KeycloakRestService {

    private final WebClient.Builder webclientBuilder;

    public KeycloakRestService(WebClient.Builder webclientBuilder) {
        this.webclientBuilder = webclientBuilder;
    }

    @Value("${keycloak.token-uri}")
    private String keycloakTokenUri;

    @Value("${keycloak.user-info-uri}")
    private String keycloakUserInfo;

    @Value("${keycloak.logout}")
    private String keycloakLogout;

    //@Value("bodgen-microservice")
    @Value("${keycloak.client-id}")
    private String clientId;

    //@Value("password")
    @Value("${keycloak.authorization-grant-type}")
    private String grantType;
    
    //@Value("refresh_token")
    @Value("${keycloak.authorization-grant-type-refresh}")
    private String grantTypeRefresh;

    //@Value("J6nucnK7cGEC0RD74R3pEe2yRTeKXolR")
    @Value("${keycloak.client-secret}")
    private String clientSecret;

    //@Value("profile")
    @Value("${keycloak.scope}")
    private String scope;

    /**
     *  login by using username and password to keycloak, and capturing token on response body
     *
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", clientId);
        map.add("grant_type", grantType);
        map.add("client_secret", clientSecret);
        map.add("scope", scope);

        BodyInserter<MultiValueMap<String, String>, ClientHttpRequest> bodyInserter =
                BodyInserters.fromFormData(map);

        return webclientBuilder
                .build()
                .post()
                //.uri("/auth/realms/mpraviap/protocol/openid-connect/token")
                .uri(keycloakTokenUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(bodyInserter)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    /**
     *  a successful user token will generate http code 200, other than that will create an exception
     *
     * @param token
     * @return
     * @throws Exception
     */
    public String checkValidity(String token) throws Exception {
        return getUserInfo(token);
    }

    /**
     *  logging out and disabling active token from keycloak
     *
     * @param refreshToken
     */
    public void logout(String refreshToken) throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", refreshToken);

        BodyInserter<MultiValueMap<String, String>, ClientHttpRequest> bodyInserter =
                BodyInserters.fromFormData(map);

        webclientBuilder
                .build()
                .post()
                //.uri("/auth/realms/mpraviap/protocol/openid-connect/logout")
                .uri(keycloakLogout)
                .body(bodyInserter)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public List<String> getRoles(String token) throws Exception {
        String response = getUserInfo(token);

        // get roles
        Map map = new ObjectMapper().readValue(response, HashMap.class);
        return (List<String>) map.get("roles");
    }

    private String getUserInfo(String token) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        HttpHeaders httpHeaders = new HttpHeaders(headers);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return webclientBuilder
                .build()
                .post()
                //.uri("/auth/realms/mpraviap/protocol/openid-connect/userinfo")
                .uri(keycloakUserInfo)
                .header(HttpHeaders.AUTHORIZATION, token)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
    /**
     *  logging out and disabling active token from keycloak
     *
     * @param refreshToken
     */
    public String refresh(String refreshToken) throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", grantTypeRefresh);
        map.add("refresh_token", refreshToken);

        BodyInserter<MultiValueMap<String, String>, ClientHttpRequest> bodyInserter =
                BodyInserters.fromFormData(map);

        return WebClient.create()
                .post()
                //.uri("/auth/realms/mpraviap/protocol/openid-connect/token")
                .uri(keycloakTokenUri)
                .body(bodyInserter)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}

