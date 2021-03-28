package com.fusionkoding.citizenshqapi.client.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipMatrixResponse {
    private Long success;
    private String code;
    private String msg;
    private List<ShipResponse> data;
}
