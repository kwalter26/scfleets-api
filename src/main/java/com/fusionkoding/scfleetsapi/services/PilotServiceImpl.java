package com.fusionkoding.scfleetsapi.services;

import com.fusionkoding.scfleetsapi.bindings.AuthVerificationBinding;
import com.fusionkoding.scfleetsapi.bindings.PilotInfoBinding;
import com.fusionkoding.scfleetsapi.db.entities.Pilot;
import com.fusionkoding.scfleetsapi.db.entities.RsiProfile;
import com.fusionkoding.scfleetsapi.db.entities.Setting;
import com.fusionkoding.scfleetsapi.db.entities.Ship;
import com.fusionkoding.scfleetsapi.db.repositories.PilotRepository;
import com.fusionkoding.scfleetsapi.dtos.PilotDTO;
import com.fusionkoding.scfleetsapi.dtos.ShipDTO;
import com.fusionkoding.scfleetsapi.utils.BadRequestException;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PilotServiceImpl implements PilotService {

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Pilot Not Found");
    private static final BadRequestException BAD_REQUEST_EXCEPTION = new BadRequestException();
    private final PilotRepository pilotRepository;
    private final RsiAccountService rsiAccountService;
    private final SettingsService settingsService;
    private final ShipService shipService;
    private final ModelMapper modelMapper;
    private final PilotInfoBinding pilotBinding;
    private final AuthVerificationBinding authVerificationBinding;

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
        pilot.getRsiProfileMap().forEach((rsiHandle, rsiProfile) -> {
            pilotBinding.getRsiPilotInfo(pilotId, rsiHandle);
        });
    }

    @Override
    public RsiProfile getRsiProfile(String pilotId, String rsiHandle) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        RsiProfile rsiProfile = pilot.getRsiProfileMap().get(rsiHandle);
        if (rsiProfile == null) {
            throw NOT_FOUND_EXCEPTION;
        }
        return rsiProfile;
    }

    @Override
    public PilotDTO createReplaceRsiPilot(String pilotId, RsiProfile rsiProfile) throws NotFoundException, BadRequestException {
        Pilot pilot = getPilot(pilotId);
        if (rsiProfile.getRsiHandle() == null) {
            throw BAD_REQUEST_EXCEPTION;
        }
        if (pilot.getRsiProfileMap().size() == 0) {
            pilot.setDefaultProfile(rsiProfile.getRsiHandle());
        }
        boolean verify = pilot.getRsiProfileMap().get(rsiProfile.getRsiHandle()) == null;


        pilot.getRsiProfileMap().put(rsiProfile.getRsiHandle(), rsiProfile);

        Pilot updatePilot = pilotRepository.save(pilot);
        getRsiPilotInfo(pilotId);
        if (verify) {
            sendVerificationRsiPilotInfo(pilotId, rsiProfile.getRsiHandle());
        }
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
        if (profile == null) {
            profile = RsiProfile.builder().rsiHandle(rsiHandle).build();
            if (pilot.getRsiProfileMap().size() == 0) {
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

        pilot.getRsiProfileMap().put(rsiHandle, profile);

        return convertToDto(pilotRepository.save(pilot));
    }

    @Override
    public PilotDTO deleteRsiProfile(String pilotId, String rsiHandle) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        pilot.getRsiProfileMap().remove(rsiHandle);
        return convertToDto(pilotRepository.save(pilot));
    }

    @Override
    public void sendVerificationRsiPilotInfo(String pilotId, String rsiHandle) throws NotFoundException {

        Setting defaultAccount = settingsService.getSettingByName("defaultAccount");

        String code = generateVerificationCode();

        updateRsiProfile(pilotId, rsiHandle, null, null, null, code, null, null, null, null, null);

        authVerificationBinding.sendPilotInfoVerification(defaultAccount.getValue(), code, pilotId, rsiHandle);
    }

    private String generateVerificationCode() {
        Random rand = new Random();
        String verificationCode = "";
        for (int i = 0; i < 10; i++) {
            verificationCode += rand.nextInt(10);
        }
        return verificationCode;
    }

    @Override
    public PilotDTO verifyRsiPilotInfo(String pilotId, String rsiHandle, String verificationCode) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        RsiProfile rsiProfile = pilot.getRsiProfileMap().get(rsiHandle);
        if (rsiProfile != null) {
            String code = rsiProfile.getVerificationCode();
            if (code.equals(verificationCode)) {
                return updateRsiProfile(pilotId, rsiHandle, null, null, true, null, null, null, null, null, null);
            }
        }
        throw NOT_FOUND_EXCEPTION;
    }

    @Override
    public List<ShipDTO> getShips(String pilotId) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        List<Ship> ships = pilot.getShips();
        if (ships == null) {
            return new ArrayList<>();
        }
        return ships.stream().map(ship -> shipService.convertToShipDto(ship)).collect(Collectors.toList());
    }

    @Override
    public ShipDTO addShip(String pilotId, String shipId) throws NotFoundException {
        Pilot pilot = getPilot(pilotId);
        ShipDTO ship = shipService.getShipById(shipId);
        pilot.getShips().add(shipService.convertToShip(ship));
        pilotRepository.save(pilot);
        return ship;
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
