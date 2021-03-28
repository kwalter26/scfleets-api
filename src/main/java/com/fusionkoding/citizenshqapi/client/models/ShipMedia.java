package com.fusionkoding.citizenshqapi.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipMedia {
    @JsonProperty("images")
    ShipImages images;
}
