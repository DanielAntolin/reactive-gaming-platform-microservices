package com.example.apigames.api.repository;


import com.example.apigames.api.commons.entities.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameUserRepository extends JpaRepository<GameUser, Long> {

    Optional<GameUser> findByUserIdAndGameId(Long userId, Long gameId);
    List<GameUser> findByUserId(Long UserId);
}
