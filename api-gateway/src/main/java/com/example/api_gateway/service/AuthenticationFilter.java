package com.example.api_gateway.service;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;



import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
  private final RouterValidator routerValidator;
  private final JwtUtils jwtUtils;
    public AuthenticationFilter(RouterValidator routerValidator, JwtUtils jwtUtils) {
        super(Config.class);
        this.routerValidator = routerValidator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            var request = exchange.getRequest();
            ServerHttpRequest serverHttpRequest = null;

            // Log the incoming request URL and headers
            System.out.println("Incoming request: " + request.getMethod() + " " + request.getURI());
            System.out.println("Request headers: " + request.getHeaders());

            if (routerValidator.isSecured.test(request)) {
                if (authMissing(request)) {
                    System.out.println("Missing Authorization header");
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // Remove "Bearer " prefix
                    System.out.println("Authorization token extracted: " + authHeader);
                } else {
                    System.out.println("Invalid Authorization header format");
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                // Check if JWT is expired
                if (jwtUtils.isexpired(authHeader)) {
                    System.out.println("JWT token is expired");
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                // Extract userId and userRole from JWT
                String userId = jwtUtils.extractUserId(authHeader).toString();
                String userRole = jwtUtils.extractUserRole(authHeader);

                // Mutate the request with userIdRequest and userRoleRequest headers
                serverHttpRequest = exchange.getRequest()
                        .mutate()
                        .header("userIdRequest", userId)  // Pass userId in header
                        .header("userRoleRequest", userRole)  // Pass userRole in header
                        .build();

                System.out.println("Request mutated with userIdRequest: " + userId + " and userRoleRequest: " + userRole);
            }

            // Proceed to the next filter in the chain
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        // Log the error response status
        System.out.println("Responding with error: " + httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    public static class Config {}
}
