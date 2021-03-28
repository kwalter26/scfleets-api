package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.db.entities.RsiProfile;
import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.utils.BadRequestException;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface PilotService {
    List<PilotDTO> getPilots();

    PilotDTO getPilotById(String pilotId) throws NotFoundException;

    PilotDTO getPilotByUsername(String username) throws NotFoundException;

    PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException;

    PilotDTO updatePilot(String pilotId, String email, String defaultProfile) throws NotFoundException;

    PilotDTO createReplaceRsiPilot(String pilotId, RsiProfile rsiProfile) throws NotFoundException, BadRequestException;

    PilotDTO updateRsiProfile(String pilotId, String rsiHandle, String rsiProfileImgUrl,
                              String timeZone, Boolean verified, String verificationCode, String ueeRecordNumber, String fluency,
                              String enlistDate, String location, String orgCode) throws NotFoundException;

    PilotDTO deleteRsiProfile(String pilotId, String rsiHandle) throws NotFoundException;

    RsiProfile getRsiProfile(String pilotId, String rsiHandle) throws NotFoundException;

    void sendVerificationRsiPilotInfo(String pilotId, String rsiHandle) throws NotFoundException;

    PilotDTO verifyRsiPilotInfo(String pilotId, String rsiHandle, String verificationCode) throws NotFoundException;

    void deletePilot(String pilotId) throws NotFoundException;

    void getRsiPilotInfo(String pilotId) throws NotFoundException;

    List<ShipDTO> getShips(String pilotId) throws NotFoundException;

    ShipDTO addShip(String pilotId, String shipId) throws NotFoundException;
}
