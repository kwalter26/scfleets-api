package com.fusionkoding.citizenshqapi.client.models;

import java.util.List;

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
public class ShipMatrixResponse {
    private Long success;
    private String code;
    private String msg;
    private List<ShipResponse> data;
}
