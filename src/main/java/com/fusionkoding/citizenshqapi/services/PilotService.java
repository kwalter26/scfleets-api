package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.entities.RsiProfile;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.dao.DuplicateKeyException;

public interface PilotService {
  List<PilotDTO> getPilots();

  PilotDTO getPilotById(String pilotId) throws NotFoundException;

  PilotDTO getPilotByUsername(String username) throws NotFoundException;

  PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException;

  PilotDTO updatePilot(String pilotId, String email) throws NotFoundException;

  PilotDTO createReplaceRsiPilot(String pilotId, String rsiHandle, RsiProfile rsiProfile) throws NotFoundException;

  PilotDTO updateRsiProfile(String pilotId, String rsiHandle, String rsiProfileImgUrl,
                            String timeZone, Boolean verified, String verificationCode, String ueeRecordNumber, String fluency,
                            String enlistDate, String location) throws NotFoundException;

  PilotDTO deleteRsiProfile(String pilotId, String rsiHandle) throws NotFoundException;

  void deletePilot(String pilotId) throws NotFoundException;

  void getRsiPilotInfo(String pilotId);
}
