package com.fusionkoding.citizenshqapi.client;

import com.fusionkoding.citizenshqapi.client.models.ShipMatrixResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RsiSiteClient {

    private static final String SHIP_MATRIX_URL = "https://robertsspaceindustries.com/ship-matrix/index";
    private final RestTemplate restTemplate;

    public ShipMatrixResponse getShipMatrix() {
        return restTemplate.getForObject(SHIP_MATRIX_URL, ShipMatrixResponse.class);
    }

}
