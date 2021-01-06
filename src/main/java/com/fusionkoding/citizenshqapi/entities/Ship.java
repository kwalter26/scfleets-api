package com.fusionkoding.citizenshqapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Double cargocapacity;
    private Double minCrew;
    private Double maxCrew;
    private Double scmSpeed;
    private Double afterburnerSpeed;
    private Double pitchMax;
    private Double yawMax;
    private Double rollMax;
    private Double xaxisAcceleration;
    private Double yaxisAcceleration;
    private Double zaxisAcceleration;
    private String timeModified;
    private String focus;
    private String description;
    private String manufacturerCode;
    private String manufacturerName;
    private String imgUrl;
}
