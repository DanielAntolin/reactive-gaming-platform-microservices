package com.example.apigames.api.controller;

import com.example.apigames.api.commons.contans.ApiPathvariables;
import com.example.apigames.api.commons.dto.GameUserRequest;
import com.example.apigames.api.commons.entities.Game;
import com.example.apigames.api.commons.entities.GameUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathvariables.V1_ROUTE + ApiPathvariables.GAMES_ROUTE)
public interface GameApi {

    @PostMapping("/saveGame")
    ResponseEntity<Game> saveGame(@RequestHeader("userIdRequest") String userId,
                                  @RequestHeader("userRoleRequest") String userRole,
                                  @RequestBody Game game);

    @GetMapping("/{id}")
    ResponseEntity<Game> getGameId(@RequestHeader("userIdRequest")String userId,
                                   @RequestHeader("userRoleRequest") String userRole,
                                   @PathVariable Long id);

    @GetMapping("/all")
    ResponseEntity<List<Game>> getGames(@RequestHeader("userIdRequest")String userId,
                                        @RequestHeader("userRoleRequest") String userRole);

    @PutMapping("/edit/{id}")
    ResponseEntity<Game> updateGame(@RequestHeader("userIdRequest")String userId,
                                    @RequestHeader("userRoleRequest") String userRole,
                                    @PathVariable Long id, @RequestBody Game game);


    @DeleteMapping("/remove/{id}")
    ResponseEntity<Game> removeGame(@RequestHeader("userIdRequest")String userId,
                                    @RequestHeader("userRoleRequest") String userRole,
                                    @PathVariable Long id);
    @PostMapping("/purchaseGame/{idGame}")
    ResponseEntity<GameUser> purchaseGame(@RequestHeader("userIdRequest") String userId,
                                          @RequestHeader("userRoleRequest") String userRole,
                                          @PathVariable Long idGame);

    @DeleteMapping("/delete/{idGame}")
    ResponseEntity<GameUser> deleteGameUser(@RequestHeader("userIdRequest") String userId,
                                            @RequestHeader("userRoleRequest") String userRole,
                                            @PathVariable Long idGame);
    @GetMapping("/fetchingGamesUser")
    ResponseEntity<List<GameUser>> getGamesUser(@RequestHeader("userIdRequest") String userId,
                                                @RequestHeader("userRoleRequest") String userRole);
}
