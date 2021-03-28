package com.fusionkoding.scfleetsapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MfaToken {
    private String token;
}
