package com.fusionkoding.citizenshqapi.client.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
