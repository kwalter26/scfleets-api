package com.fusionkoding.citizenshqapi.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsiProfile {
    private String rsiHandle;
    private String rsiProfileImgUrl;
    private String timeZone;
    private Boolean verified;
    private String verificationCode;
    private String ueeRecordNumber;
    private String fluency;
    private String enlistDate;
    private String location;
}
