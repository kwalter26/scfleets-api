package com.fusionkoding.citizenshqapi.bindings;

import com.fusionkoding.citizenshqapi.bindings.models.AuthNotify;
import com.fusionkoding.citizenshqapi.services.RsiAccountService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthStatusBinding {

    private final RsiAccountService rsiAccountService;

    @RabbitListener(queues = "#{rabbitMQConfig.getAuthQueue()}" )
    public void receivedAuthNotifyMessage(Message<AuthNotify> authNotifyMessage) {
        if(!authNotifyMessage.getPayload().isAuthenticated()){
            try {
                rsiAccountService.refreshRsiAuth();
            } catch (NotFoundException e) {
                log.error(e.getLocalizedMessage(),e);
            }
        }
    }
}
