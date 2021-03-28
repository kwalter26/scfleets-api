package com.fusionkoding.scfleetsapi.bindings.models;

import lombok.*;

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
