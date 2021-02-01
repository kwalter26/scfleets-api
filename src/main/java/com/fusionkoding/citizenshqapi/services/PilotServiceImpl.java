package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.bindings.PilotInfoBinding;
import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.entities.Pilot;
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
    Pilot pilot = pilotRepository.findById(pilotId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
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
  public PilotDTO updatePilot(String pilotId, String email, String rsiHandle, String rsiProfileImgUrl,
                              String lang, String timeZone, Boolean verified, String verificationCode, String ueeRecordNumber, String fluency,
                              String enlistDate, String country) throws NotFoundException {
    Pilot pilot = pilotRepository.findById(pilotId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    if(!email.isEmpty()) {
      pilot.setEmail(email);
    }
    if(!rsiHandle.isEmpty()) {
      pilot.setRsiHandle(rsiHandle);
    }
    if(!rsiProfileImgUrl.isEmpty()) {
      pilot.setRsiProfileImgUrl(rsiProfileImgUrl);
    }
    if(!lang.isEmpty()) {
      pilot.setLang(lang);
    }
    if(!timeZone.isEmpty()) {
      pilot.setTimeZone(timeZone);
    }
    pilot.setVerified(verified);
    if(!verificationCode.isEmpty()) {
      pilot.setVerificationCode(verificationCode);
    }
    if(!ueeRecordNumber.isEmpty()) {
      pilot.setUeeRecordNumber(ueeRecordNumber);
    }
    if(!fluency.isEmpty()) {
      pilot.setFluency(fluency);
    }
    if(!enlistDate.isEmpty()) {
      pilot.setEnlistDate(enlistDate);
    }
    if(!country.isEmpty()) {
      pilot.setCountry(country);
    }
    return convertToDto(pilotRepository.save(pilot));
  }

  @Override
  public void deletePilot(String pilotId) throws NotFoundException {
    Pilot pilot = pilotRepository.findById(pilotId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    pilotRepository.delete(pilot);
  }

  public void getRsiPilotInfo(String pilotId) {
    pilotBinding.getRsiPilotInfo(pilotId);
  }

  private PilotDTO convertToDto(Pilot org) {
    return modelMapper.map(org, PilotDTO.class);
  }

  private Pilot convertToEntity(PilotDTO orgDTO) {
    return modelMapper.map(orgDTO, Pilot.class);
  }

}
