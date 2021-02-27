package com.fusionkoding.citizenshqapi.bindings.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationRequest {
    private String accountId;
    private String pilotId;
    private String verificationCode;
    private String rsiHandle;
}
