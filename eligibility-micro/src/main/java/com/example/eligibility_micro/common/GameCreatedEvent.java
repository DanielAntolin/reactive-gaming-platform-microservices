package com.example.eligibility_micro.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GameCreatedEvent {
    private Integer id;
    private String titulo;
    private String categoria;
    private int edadMinima;
}
