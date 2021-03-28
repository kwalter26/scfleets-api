package com.fusionkoding.scfleetsapi.db.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsiAuth {
    private String token;
    private String xsrf;
    private String tavernId;
    private String deviceId;
}
