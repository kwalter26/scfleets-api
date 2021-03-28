package com.fusionkoding.citizenshqapi.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "pilots")
public class Pilot {

  public Pilot() {
    rsiProfileMap = new HashMap<>();
    ships = new ArrayList<>();
  }

  @Id
  private String id;
  @Indexed(name = "userName",unique = true)
  private String userName;
  private String email;
  private String defaultProfile;

  private Map<String,RsiProfile> rsiProfileMap = new HashMap<>();

  @DBRef
  @Builder.Default
  private List<Ship> ships = new ArrayList<>();
}
