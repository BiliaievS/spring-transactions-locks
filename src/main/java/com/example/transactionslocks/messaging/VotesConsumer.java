package com.example.transactionslocks.messaging;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.service.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class VotesConsumer implements Consumer<Votes> {

    private final MessageProcessor messageProcessor;

    @Override
    public void accept(Votes votes) {
        log.info("Message received from kafka stream: {}", votes);
        messageProcessor.processOnMessage(votes);
    }

    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> customizer() {
        return (container, dest, group) -> {
                if (group.equals("votes-consumer-group")) {
                container.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(0, 0)));
            }
        };
    }

}
