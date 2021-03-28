package com.fusionkoding.scfleetsapi.bindings;

import com.fusionkoding.scfleetsapi.bindings.models.AuthRefreshRequest;
import com.fusionkoding.scfleetsapi.bindings.models.VerificationRequest;
import com.fusionkoding.scfleetsapi.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthVerificationBinding {
    private final StreamBridge streamBridge;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;

    public void sendPilotInfoVerification(String accountId, String verificationCode, String pilotId, String rsiHandle) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getVerifyExchange(), "verify", VerificationRequest.builder().pilotId(pilotId).rsiHandle(rsiHandle).verificationCode(verificationCode).accountId(accountId).build());
    }

    public void refreshRsiAuth(String accountId, String email, String password, String deviceId) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getAuthExchange(), "reauth", AuthRefreshRequest.builder().accountId(accountId).email(email).password(password).deviceId(deviceId).build());
    }
}
