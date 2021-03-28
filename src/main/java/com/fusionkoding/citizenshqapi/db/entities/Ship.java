package com.fusionkoding.citizenshqapi.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ships")
public class Ship {
    @Id
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
