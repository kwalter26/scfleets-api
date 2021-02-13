package com.fusionkoding.citizenshqapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "pilots")
public class Pilot {

  public Pilot() {
    rsiProfileMap = new HashMap<>();
  }

  @Id
  private String id;
  private String userName;
  private String email;
  private String defaultProfile;
  @Builder.Default
  private Map<String,RsiProfile> rsiProfileMap = new HashMap<>();
}
