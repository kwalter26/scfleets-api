package com.fusionkoding.citizenshqapi.bindings.models;

import lombok.*;
import org.springframework.messaging.handler.annotation.SendTo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRefreshRequest {
    private String accountId;
    private String email;
    private String password;
    private String deviceId;
}
