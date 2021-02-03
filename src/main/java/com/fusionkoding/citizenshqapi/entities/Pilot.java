package com.fusionkoding.citizenshqapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pilots")
public class Pilot {
  @Id
  private String id;
  private String userName;
  private String email;
  private Map<String,RsiProfile> rsiProfileMap;
}
