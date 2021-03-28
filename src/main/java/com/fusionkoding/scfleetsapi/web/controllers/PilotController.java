package com.fusionkoding.scfleetsapi.web.controllers;

import com.fusionkoding.scfleetsapi.db.entities.RsiProfile;
import com.fusionkoding.scfleetsapi.dtos.PilotDTO;
import com.fusionkoding.scfleetsapi.dtos.ShipDTO;
import com.fusionkoding.scfleetsapi.dtos.VerifyDto;
import com.fusionkoding.scfleetsapi.services.PilotService;
import com.fusionkoding.scfleetsapi.utils.BadRequestException;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pilots")
@Api(tags = {"Pilots"}, value = "Pilots")
@PreAuthorize("hasRole('transactions/post')")
public class PilotController {

    public final PilotService pilotService;

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @ApiOperation(value = "Retrieve all pilots")
    @GetMapping("/")
    public ResponseEntity<List<PilotDTO>> getPilots() {
        log.debug("Getting all piltos");
        return ResponseEntity.ok(pilotService.getPilots());
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @ApiOperation(value = "Retrieved a pilot with a pilot ID")
    @GetMapping("/{pilotId}/")
    public ResponseEntity<PilotDTO> getPilot(@PathVariable String pilotId) throws NotFoundException {
        log.debug("Getting pilot for id: " + pilotId);
        return ResponseEntity.ok(pilotService.getPilotById(pilotId));
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @ApiOperation(value = "Retrieved your pilot profile")
    @GetMapping("/me/")
    public ResponseEntity<PilotDTO> getPilot(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        log.debug("Getting pilot for id: " + jwt.getSubject());
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();
        try {
            PilotDTO pilot = pilotService.getPilotById(sub);
            pilotService.getRsiPilotInfo(pilot.getId());
            return ResponseEntity.ok(pilot);
        } catch (NotFoundException ex) {
            log.info("Pilot not found for security principal. Creating initial user entry for user: " + sub);
            PilotDTO newPilot = pilotService.createPilot(PilotDTO.builder().id(sub).userName(jwt.getClaimAsString("cognito:username")).email(jwt.getClaimAsString("email")).build());
            return ResponseEntity.ok(newPilot);
        }
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @ApiOperation(value = "Retrieved your pilot profile")
    @PatchMapping("/me/")
    public ResponseEntity<PilotDTO> updatePilot(@AuthenticationPrincipal Jwt jwt, Principal principal, @RequestParam(required = false) String email, @RequestParam(required = false) String defaultProfile) throws NotFoundException {
        log.debug("Updating pilot for id: " + jwt.getSubject());
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();
        return ResponseEntity.ok(pilotService.updatePilot(sub, email, defaultProfile));
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @PostMapping("/{piltoId}/profiles/")
    public ResponseEntity<PilotDTO> addPilotInfo(@PathVariable String piltoId, @RequestBody RsiProfile rsiProfile) throws NotFoundException, BadRequestException {

        PilotDTO pilotDTO = pilotService.createReplaceRsiPilot(piltoId, rsiProfile);

        return ResponseEntity.ok(pilotDTO);
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @PostMapping("/me/profiles/")
    public ResponseEntity<PilotDTO> addPilotInfo(@AuthenticationPrincipal Jwt jwt, @RequestBody RsiProfile rsiProfile) throws NotFoundException, BadRequestException {
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();

        PilotDTO pilotDTO = pilotService.createReplaceRsiPilot(sub, rsiProfile);

        return ResponseEntity.ok(pilotDTO);
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @DeleteMapping("/me/profiles/{rsiHandle}/")
    public ResponseEntity<PilotDTO> deletePilotInfo(@AuthenticationPrincipal Jwt jwt, @PathVariable String rsiHandle) throws NotFoundException {
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();

        PilotDTO pilotDTO = pilotService.deleteRsiProfile(sub, rsiHandle);

        return ResponseEntity.ok(pilotDTO);
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @GetMapping("/me/profiles/{rsiHandle}/verify/")
    public ResponseEntity<PilotDTO> sendVerifyPilotInfo(@AuthenticationPrincipal Jwt jwt, @PathVariable String rsiHandle) throws NotFoundException {
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();

        pilotService.sendVerificationRsiPilotInfo(sub, rsiHandle);

        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @GetMapping("/{pilotId}/profiles/{rsiHandle}/verify/")
    public ResponseEntity<PilotDTO> sendVerifyPilotInfo(@PathVariable String pilotId, @PathVariable String rsiHandle) throws NotFoundException {

        pilotService.sendVerificationRsiPilotInfo(pilotId, rsiHandle);

        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasAnyRole('pilot','admin','transactions/post')")
    @PostMapping("/me/profiles/{rsiHandle}/verify/")
    public ResponseEntity<PilotDTO> verifyPilotInfo(@AuthenticationPrincipal Jwt jwt, @PathVariable String rsiHandle, @RequestBody VerifyDto verifyDto) throws NotFoundException {
        if (jwt.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String sub = jwt.getSubject();

        PilotDTO pilotDTO = pilotService.verifyRsiPilotInfo(sub, rsiHandle, verifyDto.getVerificationCode());

        return ResponseEntity.ok(pilotDTO);
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @PatchMapping("/{piltoId}/profiles/{rsiHandle}/")
    public ResponseEntity<PilotDTO> updatePilotInfo(@PathVariable String piltoId,
                                                    @PathVariable String rsiHandle,
                                                    @RequestBody RsiProfile rsiProfile)
            throws NotFoundException {

        PilotDTO pilotDTO = pilotService.updateRsiProfile(piltoId, rsiHandle,
                rsiProfile.getRsiProfileImgUrl(),
                rsiProfile.getTimeZone(),
                rsiProfile.getVerified(),
                rsiProfile.getVerificationCode(),
                rsiProfile.getUeeRecordNumber(),
                rsiProfile.getFluency(),
                rsiProfile.getEnlistDate(),
                rsiProfile.getLocation(),
                rsiProfile.getOrgSymbol());

        return ResponseEntity.ok(pilotDTO);
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @GetMapping("/{pilotId}/ships/")
    public ResponseEntity<List<ShipDTO>> getPilotsShips(@PathVariable String pilotId) throws NotFoundException {
        return ResponseEntity.ok(pilotService.getShips(pilotId));
    }

    @PreAuthorize("hasAnyRole('admin','transactions/post')")
    @PostMapping("/{pilotId}/ships/")
    public ResponseEntity<ShipDTO> addPilotsShip(@PathVariable String pilotId, @RequestParam String shipId) throws NotFoundException {
        return ResponseEntity.ok(pilotService.addShip(pilotId, shipId));
    }


}
