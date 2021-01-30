package com.fusionkoding.citizenshqapi.controllers;

import java.security.Principal;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.services.PilotService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
@Api(tags = { "Pilots" }, value = "Pilots")
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
  public ResponseEntity<PilotDTO> getPilot(@AuthenticationPrincipal Jwt jwt) {
    log.debug("Getting pilot for id: " + jwt.getSubject());
    if (jwt.getSubject().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    String sub = jwt.getSubject();
    try {
      return ResponseEntity.ok(pilotService.getPilotById(sub));
    } catch (NotFoundException ex) {
      log.info("Pilot not found for security principal. Creating initial user entry for user: " + sub);
      PilotDTO newPilot = pilotService.createPilot(PilotDTO.builder().id(sub).userName(jwt.getClaimAsString("cognito:username")).email(jwt.getClaimAsString("email")).build());
      return ResponseEntity.ok(newPilot);
    }
  }

}
