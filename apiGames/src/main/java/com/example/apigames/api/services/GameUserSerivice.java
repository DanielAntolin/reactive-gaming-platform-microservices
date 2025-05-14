package com.example.apigames.api.services;

import com.example.apigames.api.commons.entities.GameUser;

import java.util.List;

public interface GameUserSerivice {

    GameUser purchaseGame(GameUser gameUser);
    void deleteGameUser(Long idGame, Long idUser );
    List<GameUser> getAllGameUsers(Long idUser);
}
