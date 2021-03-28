package com.fusionkoding.scfleetsapi.client;

import com.fusionkoding.scfleetsapi.client.models.OrgRequestBody;
import com.fusionkoding.scfleetsapi.client.models.RsiResponse;
import com.fusionkoding.scfleetsapi.client.models.ShipMatrixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class RsiSiteClient {

    private static final String GET_ORGS = "https://robertsspaceindustries.com/api/orgs/getOrgs";
    private static final String SHIP_MATRIX_URL = "https://robertsspaceindustries.com/ship-matrix/index";
    private final RestTemplate restTemplate;

    public ShipMatrixResponse getShipMatrix() {
        return restTemplate.getForObject(SHIP_MATRIX_URL, ShipMatrixResponse.class);
    }

    public RsiResponse getOrgs(long pageNumber) {
        OrgRequestBody orgRequest = OrgRequestBody.builder().page(pageNumber).build();
        return restTemplate.postForObject(GET_ORGS, orgRequest, RsiResponse.class);
    }

}
