package com.fusionkoding.citizenshqapi.dtos;

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
public class PilotDTO {
  private String id;
  private String userName;
  private String email;
  private String rsiHandle;
  private String rsiProfileImgUrl;
  private String lang;
  private String timeZone;
  private Boolean verfied;
  private String verificationCode;
  private String ueeRecordNumber;
  private String fluency;
  private String enlistDate;
  private String country;
}
