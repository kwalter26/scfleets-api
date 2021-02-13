package com.fusionkoding.citizenshqapi.dtos;

import com.fusionkoding.citizenshqapi.entities.RsiProfile;
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
public class PilotDTO {
  private String id;
  private String userName;
  private String email;
  private String defaultProfile;
  private Map<String, RsiProfile> rsiProfileMap;
}
