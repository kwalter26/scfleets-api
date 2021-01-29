package com.fusionkoding.citizenshqapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
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
  public ResponseEntity<PilotDTO> getPilot(Principal principal) throws NotFoundException {
      log.debug("Getting pilot for id: " + principal.getName());
      return ResponseEntity.ok(pilotService.getPilotById(principal.getName()));
  }

}
