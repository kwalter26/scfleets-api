package com.fusionkoding.scfleetsapi.db.entities;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsiProfile {
    @NotNull
    private String rsiHandle;
    private String rsiProfileImgUrl;
    private String timeZone;
    private Boolean verified;
    private String verificationCode;
    private String ueeRecordNumber;
    private String fluency;
    private String enlistDate;
    private String location;
    private String orgSymbol;
}
