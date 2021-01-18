package com.fusionkoding.citizenshqapi.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ShipImages {
    @JsonProperty("store_small")
    String small;
    @JsonProperty("store_large")
    String large;
}
