package com.fusionkoding.citizenshqapi.controllers;

import java.security.Principal;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.services.PilotService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pilots")
@Api(tags = { "Pilots" }, value = "Pilots", description = "Routes used for maintaining pilot data.")
public class PilotController {
  public final PilotService pilotService;

  @PreAuthorize("hasAnyRole('admin')")
  @ApiOperation(value = "Retrieved a pilot with a pilot ID")
  @GetMapping("/{pilotId}/")
  public ResponseEntity<PilotDTO> getPilot(@PathVariable String pilotId) throws NotFoundException {
    log.debug("Getting pilot for id: " + pilotId);
    return ResponseEntity.ok(pilotService.getPilotById(pilotId));
  }

  @PreAuthorize("hasAnyRole('pilot','admin')")
  @ApiOperation(value = "Retrieved your pilot profile")
  @GetMapping("/me/")
  public ResponseEntity<PilotDTO> getPilot(Principal principal) {
    log.debug("Getting pilot for id: " + principal.getName());
    if (principal.getName().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    String username = principal.getName();
    try {
      return ResponseEntity.ok(pilotService.getPilotByUsername(username));
    } catch (NotFoundException ex) {
      log.info("Pilot not found for security principal. Creating initial user entry for user: " + username);
      PilotDTO newPilot = pilotService.createPilot(PilotDTO.builder().userName(username).build());
      return ResponseEntity.ok(newPilot);
    }
  }

}
