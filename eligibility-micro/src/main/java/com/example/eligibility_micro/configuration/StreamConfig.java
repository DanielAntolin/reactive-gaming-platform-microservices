package com.example.eligibility_micro.configuration;

import com.example.eligibility_micro.common.GameCreatedEvent;
import com.example.eligibility_micro.common.GameEligibleEvent;
import com.example.eligibility_micro.processors.EligibilityProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {
@Bean
    public Function<Flux<GameCreatedEvent>, Flux<GameEligibleEvent>>  gameCreatedBinding(final EligibilityProcessor processor){
    return processor::process;
}
}
