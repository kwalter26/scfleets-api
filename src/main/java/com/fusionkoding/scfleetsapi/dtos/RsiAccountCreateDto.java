package com.fusionkoding.scfleetsapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsiAccountCreateDto {
    private String email;
    private String password;
}
