package com.example.apigames.api.services.Impl;

import com.example.apigames.api.commons.entities.GameUser;
import com.example.apigames.api.commons.exceptions.GameException;
import com.example.apigames.api.repository.GameUserRepository;
import com.example.apigames.api.services.GameUserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserSerivice {
    @Autowired
    private GameUserRepository gameUserRepository;
    @Override
    public GameUser purchaseGame(GameUser gameUser) {
       try{
           return gameUserRepository.save(gameUser);
       } catch (Exception e){
           throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error purchase game");
       }
    }

    @Override
    public void deleteGameUser(Long idGame, Long idUser) {
        GameUser gameUser = gameUserRepository.findByUserIdAndGameId(idUser, idGame)
                .orElseThrow(() ->  new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error delete game user"));

        gameUserRepository.delete(gameUser);
    }

    @Override
    public List<GameUser> getAllGameUsers(Long idUser) {
        return gameUserRepository.findByUserId(idUser);
    }
}
