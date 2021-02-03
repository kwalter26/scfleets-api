package com.fusionkoding.citizenshqapi.bindings;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component
public class PilotInfoBinding {

    private final StreamBridge streamBridge;

    @Bean
    private Supplier<Message<String>> getRsiPilotInfo() {
        return () -> MessageBuilder.withPayload("pilotId").build();
    }

    public void getRsiPilotInfo(String pilotId) {
        streamBridge.send("pilot-info-events", MessageBuilder.withPayload(pilotId).build());
    }
}
