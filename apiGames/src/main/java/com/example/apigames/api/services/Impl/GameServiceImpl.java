package com.example.apigames.api.services.Impl;

import com.example.apigames.api.commons.contans.TopicConstants;
import com.example.apigames.api.commons.entities.Game;
import com.example.apigames.api.commons.exceptions.GameException;
import com.example.apigames.api.repository.GameRepository;
import com.example.apigames.api.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final StreamBridge streamBridge;

    public GameServiceImpl(GameRepository gameRepository, StreamBridge streamBridge) {
        this.gameRepository = gameRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public Game getGameId(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(()-> new GameException(HttpStatus.NOT_FOUND, "Game not found"));
    }
    @Override
    public Game createGame(Game game) {

        try {
            return Optional.of(game)
                    .map(gameRepository::save)
                    .map(this::sendGameEvent)
                    .orElseThrow(() -> new GameException(HttpStatus.BAD_REQUEST, "Error saving game"));
        } catch (Exception e) {
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating game");
        }
    }

    private Game sendGameEvent(Game game) {
        Optional.of(game)
                .map(given -> this.streamBridge.send(TopicConstants.GAME_CREATED_TOPIC,game))
                .map(bool -> game);
        return game;
    }

    @Override
    public Game updategame(Long id, Game game) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game updateGame = gameOptional.get();
            updateGame.setTitulo(game.getTitulo());
            updateGame.setCategoria(game.getCategoria());

            try {
                return gameRepository.save(updateGame);
            } catch (Exception e) {
                throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating game");
            }
        } else {
            throw new GameException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }

    @Override
    public void deleteGame(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            try {
                gameRepository.delete(gameOptional.get());
            } catch (Exception e) {
                throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting game");
            }
        } else {
            throw new GameException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }

    @Override
    public List<Game> getAllGames() {
        try {
            return gameRepository.findAll();
        } catch (Exception e) {
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching games");
        }
    }

}
