package com.fusionkoding.scfleetsapi.bindings.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoRequest {
    String pilotId;
    String rsiHandle;
}
