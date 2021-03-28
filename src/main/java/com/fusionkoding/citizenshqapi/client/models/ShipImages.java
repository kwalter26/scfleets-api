package com.fusionkoding.citizenshqapi.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipImages {
    @JsonProperty("store_small")
    String small;
    @JsonProperty("store_large")
    String large;
}
