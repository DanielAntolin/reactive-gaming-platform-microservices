package com.example.apigames.api.controller.impl;

import com.example.apigames.api.commons.dto.GameUserRequest;
import com.example.apigames.api.commons.entities.Game;
import com.example.apigames.api.commons.entities.GameUser;
import com.example.apigames.api.commons.exceptions.GameException;
import com.example.apigames.api.controller.GameApi;
import com.example.apigames.api.repository.GameUserRepository;
import com.example.apigames.api.services.GameService;
import com.example.apigames.api.services.GameUserSerivice;
import com.example.apigames.api.services.Impl.GameServiceImpl;
import com.example.apigames.api.services.Impl.GameUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

public class GameController implements GameApi {
    private final GameService gameService;
    private final GameUserSerivice gameUserSerivice;
    private final GameUserServiceImpl gameUserServiceImpl;
    private final GameUserRepository gameUserRepository;

    @Autowired
    public GameController(GameServiceImpl gameService, GameUserSerivice gameUserSerivice, GameUserServiceImpl gameUserServiceImpl, GameUserRepository gameUserRepository) {
        this.gameService = gameService;
        this.gameUserSerivice = gameUserSerivice;
        this.gameUserServiceImpl = gameUserServiceImpl;
        this.gameUserRepository = gameUserRepository;
    }


    @Override
    public ResponseEntity<Game> saveGame(String userId,String userRole, @RequestBody Game game) {
        if(userRole.equals("ROLE_ADMIN")){
            Game gameCreate = gameService.createGame(game);
            return ResponseEntity.ok(gameCreate);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Game> getGameId(String userId,String userRole, Long id) {
        Optional<Game> gameRecup = Optional.ofNullable(gameService.getGameId(id));
        return ResponseEntity.ok(gameRecup.orElse(null));
    }

    @Override
    public ResponseEntity<List<Game>> getGames(String userId,String userRole) {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @Override
    public ResponseEntity<Game> updateGame(String userId,String userRole, Long id, Game game) {
        if(userRole.equals("ROLE_ADMIN")){
            return  ResponseEntity.ok(gameService.updategame(id, game));
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Game> removeGame(String userId,String userRole, Long id) {
        try {
            if(userRole.equals("ROLE_ADMIN")){
                gameService.deleteGame(id);
                return ResponseEntity.ok().build();
            }

        } catch (GameException e) {
            return ResponseEntity.status(e.getHttpStatus()).build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<GameUser> purchaseGame(String userId, String userRole, Long idGame) {
        Long longId = Long.parseLong(userId);


        GameUser gameUserCreate = GameUser.builder()
                .userId(longId)
                .gameId(idGame)
                .purchaseDate(LocalDate.now())
                .build();

        GameUser savedGameUser = gameUserServiceImpl.purchaseGame(gameUserCreate);
        return ResponseEntity.ok(savedGameUser);
    }

    @Override
    public ResponseEntity<GameUser> deleteGameUser(String userId, String userRole, Long idGame) {
        Long longId = Long.parseLong(userId);
        gameUserSerivice.deleteGameUser(idGame,longId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<GameUser>> getGamesUser(String userId, String userRole) {
        Long longId = Long.parseLong(userId);
        return ResponseEntity.ok(gameUserRepository.findByUserId(longId));
    }

}
