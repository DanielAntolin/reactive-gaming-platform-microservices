package com.example.apigames.api.commons.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private Integer codeStatus;
    private String message;
}
