package com.fusionkoding.citizenshqapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "pilots")

public class Pilot {
  @Id
  private String id;
  private String userName;
  private String email;
  private String rsiHandle;
  private String rsiProfileImgUrl;
  private String lang;
  private String timeZone;
  private Boolean verified;
  private String verificationCode;
  private String ueeRecordNumber;
  private String fluency;
  private String enlistDate;
  private String country;
}
