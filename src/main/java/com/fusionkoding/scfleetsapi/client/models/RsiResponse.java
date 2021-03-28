package com.fusionkoding.scfleetsapi.client.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsiResponse {
    String success;
    String code;
    String msg;
    OrgData data;
}
