package com.fusionkoding.citizenshqapi.bindings;

import com.fusionkoding.citizenshqapi.bindings.models.InfoRequest;
import com.fusionkoding.citizenshqapi.bindings.models.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component
public class AuthVerificationBinding {
    private final StreamBridge streamBridge;

    @Bean
    private Supplier<Message<VerificationRequest>> sendPilotInfoVerification() {
        return () -> MessageBuilder.withPayload(VerificationRequest.builder().build()).build();
    }

    public void sendPilotInfoVerification(String accountId, String verificationCode, String pilotId, String rsiHandle) {
        streamBridge.send("rsi-verify", MessageBuilder.withPayload(VerificationRequest.builder().pilotId(pilotId).rsiHandle(rsiHandle).verificationCode(verificationCode).accountId(accountId).build()).build());
    }
}
