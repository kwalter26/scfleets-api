package com.fusionkoding.citizenshqapi.dtos;

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
}
