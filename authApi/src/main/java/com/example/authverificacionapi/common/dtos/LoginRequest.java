package com.example.authverificacionapi.common.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
