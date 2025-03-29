package com.example.eligibility_micro.processors;

import com.example.eligibility_micro.common.GameCreatedEvent;
import com.example.eligibility_micro.common.GameEligibleEvent;
import com.example.eligibility_micro.services.GameEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityProcessor {
    private final GameEligibleService gameEligibleService;

    public EligibilityProcessor(GameEligibleService gameEligibleService) {
        this.gameEligibleService = gameEligibleService;
    }

    public Flux<GameEligibleEvent> process(Flux<GameCreatedEvent>gameCreatedEventFlux){
        return gameCreatedEventFlux.doOnNext(given -> log.info("entry event: {}", given))
                .flatMap(gameEligibleService::eligibiltyGame)
                .onErrorContinue(this::handError);

    }

    private void handError(Throwable throwable, Object o) {
        log.error("Error processing event {}",o, throwable);
    }
}
