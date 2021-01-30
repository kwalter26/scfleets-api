package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.dao.DuplicateKeyException;

public interface PilotService {
  public List<PilotDTO> getPilots();

  public PilotDTO getPilotById(String pilotId) throws NotFoundException;

  public PilotDTO getPilotByUsername(String username) throws NotFoundException;

  public PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException;

  public PilotDTO updatePilot(String pilotId, String userName, String email, String rsiHandle, String rsiProfileImgUrl,
      String lang, String timeZone, Boolean verfied, String verificationCode, String ueeRecordNumber, String fluency,
      String enlistDate, String country) throws NotFoundException;

  public void deletePilot(String pilotId) throws NotFoundException;
}
