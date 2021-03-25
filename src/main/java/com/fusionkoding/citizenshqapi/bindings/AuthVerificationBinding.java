package com.fusionkoding.citizenshqapi.bindings;

import com.fusionkoding.citizenshqapi.bindings.models.AuthNotify;
import com.fusionkoding.citizenshqapi.bindings.models.AuthRefreshRequest;
import com.fusionkoding.citizenshqapi.bindings.models.InfoRequest;
import com.fusionkoding.citizenshqapi.bindings.models.VerificationRequest;
import com.fusionkoding.citizenshqapi.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderInitializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthVerificationBinding {
    private final StreamBridge streamBridge;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;

    public void sendPilotInfoVerification(String accountId, String verificationCode, String pilotId, String rsiHandle) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getVerifyExchange(),"verify", VerificationRequest.builder().pilotId(pilotId).rsiHandle(rsiHandle).verificationCode(verificationCode).accountId(accountId).build());
    }

    public void refreshRsiAuth(String accountId, String email, String password, String deviceId) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getAuthExchange(),"reauth", AuthRefreshRequest.builder().accountId(accountId).email(email).password(password).deviceId(deviceId).build());
    }
}
