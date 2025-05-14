package com.example.apigames.api.repository;


import com.example.apigames.api.commons.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
