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
@Document(collection = "orgs")
public class Org {
    @Id
    private String id;
    private String name;
    private String symbol;
    private String description;
    private String leaderHandle;
    private String imageUrl;
    private String archeType;
    private String lang;
    private String commitment;
    private Boolean recruiting;
    private Boolean rolePlay;
    private Long members;
    private String uri;
}
