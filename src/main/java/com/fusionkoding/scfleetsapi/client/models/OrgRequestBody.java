package com.fusionkoding.scfleetsapi.client.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrgRequestBody {
    String sort;
    String search;
    List<String> commitment;
    List<String> roleplay;
    List<String> size;
    List<String> model;
    List<String> activity;
    List<String> language;
    List<String> recuiting;
    long pagesize;
    long page;
}
