package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.bindings.PilotInfoBinding;
import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.entities.Pilot;
import com.fusionkoding.citizenshqapi.entities.RsiProfile;
import com.fusionkoding.citizenshqapi.repositories.PilotRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final ModelMapper modelMapper;
    private final PilotInfoBinding pilotBinding;
    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Pilot Not Found");

    @Override
    public List<PilotDTO> getPilots() {
        log.debug("Getting all pilots");
        List<Pilot> pilots = pilotRepository.findAll();
        log.debug("Retrieved all pilots: size " + pilots.size());
        return pilots.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PilotDTO getPilotById(String pilotId) throws NotFoundException {
        log.debug("Getting pilot for id: " + pilotId);
        Pilot pilot = getPilot(pilotId);
        log.debug("Found pilot " + pilot.getUserName() + " for id: " + pilotId);
        return convertToDto(pilot);
    }

    @Override
    public PilotDTO getPilotByUsername(String userName) throws NotFoundException {
        log.debug("Getting pilot for id: " + userName);
        Pilot pilot = pilotRepository.findByUserName(userName).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        log.debug("Found pilot " + pilot.getUserName() + " for id: " + userName);
        return convertToDto(pilot);
    }

    @Override
    public PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException {
        log.debug("Creating pilot for username: " + pilotDTO.getUserName());
        Pilot pilot = pilotRepository.save(convertToEntity(pilotDTO));
        return convertToDto(pilot);
    }

    @Override
    public PilotDTO updatePilot(String pilotId, String email, String defaultProfile) throws NotFoundException {
        Pilot pilot = pilotRepository.findById(pilotId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        if (email != null && !email.isEmpty()) {
            pilot.setEmail(email);
        }
        if (defaultProfile != null && !defaultProfile.isEmpty() && pilot.getRsiProfileMap().containsKey(defaultProfile)) {
            pilot.setDefaultProfile(defaultProfile);
        }
        return convertToDto(pilotRepository.save(pilot));
    }

    @Override
    public void deletePilot(String pilotId) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        pilotRepository.delete(pilot);
    }

    @Override
    public void getRsiPilotInfo(String pilotId) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        pilot.getRsiProfileMap().forEach((rsiHandle,rsiProfile)->{
            pilotBinding.getRsiPilotInfo(pilotId, rsiHandle);
        });
    }

    @Override
    public PilotDTO createReplaceRsiPilot(String pilotId, RsiProfile rsiProfile) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        if(rsiProfile.getRsiHandle() == null){
            throw NOT_FOUND_EXCEPTION;
        }
        if(pilot.getRsiProfileMap().size()==0){
            pilot.setDefaultProfile(rsiProfile.getRsiHandle());
        }
        pilot.getRsiProfileMap().put(rsiProfile.getRsiHandle(), rsiProfile);
        Pilot updatePilot = pilotRepository.save(pilot);
        getRsiPilotInfo(pilotId);
        return convertToDto(updatePilot);
    }

    @Override
    public PilotDTO updateRsiProfile(String pilotId,
                                     String rsiHandle,
                                     String rsiProfileImgUrl,
                                     String timeZone,
                                     Boolean verified,
                                     String verificationCode,
                                     String ueeRecordNumber,
                                     String fluency,
                                     String enlistDate,
                                     String location, String orgSymbol) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        RsiProfile profile = pilot.getRsiProfileMap().get(rsiHandle);
        if(profile==null){
            profile = RsiProfile.builder().rsiHandle(rsiHandle).build();
            if(pilot.getRsiProfileMap().size()==0){
                pilot.setDefaultProfile(rsiHandle);
            }
        }

        if (rsiProfileImgUrl != null) {
            profile.setRsiProfileImgUrl(rsiProfileImgUrl);
        }
        if (timeZone != null) {
            profile.setTimeZone(timeZone);
        }
        if (verified != null) {
            profile.setVerified(verified);
        }
        if (verificationCode != null) {
            profile.setVerificationCode(verificationCode);
        }
        if (ueeRecordNumber != null) {
            profile.setUeeRecordNumber(ueeRecordNumber);
        }
        if (fluency != null) {
            profile.setFluency(fluency);
        }
        if (enlistDate != null) {
            profile.setEnlistDate(enlistDate);
        }
        if (location != null) {
            profile.setLocation(location);
        }
        if (orgSymbol != null) {
            profile.setOrgSymbol(orgSymbol);
        }

        pilot.getRsiProfileMap().put(rsiHandle,profile);

        return convertToDto(pilotRepository.save(pilot));
    }

    @Override
    public PilotDTO deleteRsiProfile(String pilotId, String rsiHandle) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        pilot.getRsiProfileMap().remove(rsiHandle);
        return convertToDto(pilotRepository.save(pilot));
    }

    private Pilot getPilot(String pilotId) throws NotFoundException {
        return pilotRepository.findById(pilotId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    private PilotDTO convertToDto(Pilot org) {
        return modelMapper.map(org, PilotDTO.class);
    }

    private Pilot convertToEntity(PilotDTO orgDTO) {
        return modelMapper.map(orgDTO, Pilot.class);
    }

}
