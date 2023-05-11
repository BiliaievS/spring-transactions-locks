package com.example.transactionslocks.messaging;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.service.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class StreamConfig {

    private final MessageProcessor messageProcessor;

    @Bean
    public Consumer<Votes> votesConsumer() {
        return value -> {
            log.info("Read new vote message: " + value);
            messageProcessor.processOnMessage(value);
        };
    }

}
