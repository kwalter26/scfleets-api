package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.dao.DuplicateKeyException;

public interface PilotService {
  List<PilotDTO> getPilots();

  PilotDTO getPilotById(String pilotId) throws NotFoundException;

  PilotDTO getPilotByUsername(String username) throws NotFoundException;

  PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException;

  PilotDTO updatePilot(String pilotId, String email, String rsiHandle, String rsiProfileImgUrl,
      String lang, String timeZone, Boolean verified, String verificationCode, String ueeRecordNumber, String fluency,
      String enlistDate, String country) throws NotFoundException;

  void deletePilot(String pilotId) throws NotFoundException;
}
