package com.fusionkoding.citizenshqapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyDto {
    private String verificationCode;
}
