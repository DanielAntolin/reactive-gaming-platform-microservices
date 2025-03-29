package com.example.api_gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {
    public static final List<String> OpenEndpoints = List.of(
            "/auth"
    );
    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            OpenEndpoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
