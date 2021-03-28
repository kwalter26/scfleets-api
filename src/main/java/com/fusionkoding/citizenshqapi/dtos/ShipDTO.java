package com.fusionkoding.citizenshqapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipDTO {
    private String id;
    private Long rsiId;
    private String name;
    private String productionStatus;
    private Double length;
    private Double beam;
    private Double height;
    private String size;
    private Double mass;
    private String type;
    private Double cargoCapacity;
    private Double minCrew;
    private Double maxCrew;
    private Double scmSpeed;
    private Double afterburnerSpeed;
    private Double pitchMax;
    private Double yawMax;
    private Double rollMax;
    private Double xAxisAcceleration;
    private Double yAxisAcceleration;
    private Double zAxisAcceleration;
    private String timeModified;
    private String focus;
    private String description;
    private String manufacturerCode;
    private String manufacturerName;
    private String imgUrl;
}
