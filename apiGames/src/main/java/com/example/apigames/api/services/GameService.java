package com.example.apigames.api.services;

import com.example.apigames.api.commons.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game getGameId(Long id);
    Game createGame(Game user);
    Game updategame(Long id,Game game);
    void deleteGame(Long id);
    List<Game> getAllGames();
}

