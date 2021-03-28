package com.fusionkoding.citizenshqapi.dtos;

import com.fusionkoding.citizenshqapi.db.entities.RsiProfile;
import lombok.*;

import java.util.List;
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
    private List<ShipDTO> ships;
}
