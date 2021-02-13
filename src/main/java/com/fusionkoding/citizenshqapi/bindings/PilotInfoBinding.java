package com.fusionkoding.citizenshqapi.bindings;

import com.fusionkoding.citizenshqapi.bindings.models.InfoRequest;
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
    private Supplier<Message<InfoRequest>> getRsiPilotInfo() {
        return () -> MessageBuilder.withPayload(InfoRequest.builder().build()).build();
    }

    public void getRsiPilotInfo(String pilotId, String rsiHandle) {
        streamBridge.send("pilot-info-events", MessageBuilder.withPayload(InfoRequest.builder().pilotId(pilotId).rsiHandle(rsiHandle).build()).build());
    }
}
