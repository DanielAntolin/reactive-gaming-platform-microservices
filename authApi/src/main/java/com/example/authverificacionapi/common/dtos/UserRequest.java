package com.example.authverificacionapi.common.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String role;

}
