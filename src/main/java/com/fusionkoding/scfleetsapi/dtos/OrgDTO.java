package com.fusionkoding.scfleetsapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrgDTO {
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
    private long members;
    private String uri;
}
