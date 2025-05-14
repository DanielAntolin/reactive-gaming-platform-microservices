package com.example.apigames.api.commons.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table (name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String titulo;
    private String categoria;
    private int edadMinima;

}
