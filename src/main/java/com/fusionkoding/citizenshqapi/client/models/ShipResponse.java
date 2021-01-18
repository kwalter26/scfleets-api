package com.fusionkoding.citizenshqapi.client.models;

import java.util.List;

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
public class ShipResponse {
    @JsonProperty("id")
    private Long rsiId;
    private String name;
    @JsonProperty("production_status")
    private String productionStatus;
    private Double length;
    private Double beam;
    private Double height;
    private String size;
    private Double mass;
    private String type;
    @JsonProperty("cargocapacity")
    private Double cargoCapacity;
    @JsonProperty("min_crew")
    private Double minCrew;
    @JsonProperty("max_crew")
    private Double maxCrew;
    @JsonProperty("scm_speed")
    private Double scmSpeed;
    @JsonProperty("afterburner_speed")
    private Double afterburnerSpeed;
    @JsonProperty("pitch_max")
    private Double pitchMax;
    @JsonProperty("yaw_max")
    private Double yawMax;
    @JsonProperty("roll_max")
    private Double rollMax;
    @JsonProperty("xaxis_acceleration")
    private Double xAxisAcceleration;
    @JsonProperty("yaxis_acceleration")
    private Double yAxisAcceleration;
    @JsonProperty("zaxis_acceleration")
    private Double zAxisAcceleration;
    @JsonProperty("time_modified.unfiltered")
    private String timeModified;
    private String focus;
    private String description;
    private String imgUrl;

    private Manufacturer manufacturer;
    private List<ShipMedia> media;
}
