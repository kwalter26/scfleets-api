package com.fusionkoding.citizenshqapi.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
