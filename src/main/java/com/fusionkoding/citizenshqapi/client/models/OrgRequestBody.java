package com.fusionkoding.citizenshqapi.client.models;

import java.util.List;

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
