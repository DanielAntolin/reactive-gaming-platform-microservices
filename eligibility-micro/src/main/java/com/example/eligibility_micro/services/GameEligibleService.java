package com.example.eligibility_micro.services;

import com.example.eligibility_micro.common.GameCreatedEvent;
import com.example.eligibility_micro.common.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface GameEligibleService {
    Mono<GameEligibleEvent> eligibiltyGame(GameCreatedEvent gameCreatedEvent);
}
