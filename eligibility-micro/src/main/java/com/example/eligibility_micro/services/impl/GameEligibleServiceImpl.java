package com.example.eligibility_micro.services.impl;

import com.example.eligibility_micro.common.GameCreatedEvent;
import com.example.eligibility_micro.common.GameEligibleEvent;
import com.example.eligibility_micro.services.GameEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class GameEligibleServiceImpl implements GameEligibleService {
    @Override
    public Mono<GameEligibleEvent> eligibiltyGame(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> GameEligibleEvent.builder()
                        .id(givenCreated.getId())
                        .titulo(givenCreated.getTitulo())
                        .categoria(givenCreated.getCategoria())
                        .edadMinima(givenCreated.getEdadMinima())
                        .isEligible(true)
                        .build()
                );
    }

    private Mono<GameCreatedEvent> checkIsEligible(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .filter(givent -> givent.getEdadMinima() > 7)
                .map(given -> gameCreatedEvent);
    }
}
