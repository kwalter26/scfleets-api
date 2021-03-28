package com.fusionkoding.scfleetsapi.web.controllers;

import com.fusionkoding.scfleetsapi.dtos.OrgDTO;
import com.fusionkoding.scfleetsapi.services.OrgService;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orgs")
@Api(tags = {"Orgs"}, value = "Orgs", description = "Routes used for maintaining origization data.")
public class OrgController {

    private final OrgService orgService;

    @PreAuthorize("hasAnyRole('admin','pilot')")
    @ApiOperation(value = "View a list of available orgs")
    @GetMapping("/")
    public ResponseEntity<List<OrgDTO>> getOrgs() {
        log.debug("Getting all orgs");
        return ResponseEntity.ok(orgService.getOrgs());
    }

    @PreAuthorize("hasAnyRole('admin','pilot')")
    @ApiOperation(value = "Retrieved an org with and org ID")
    @GetMapping("/{orgId}/")
    public ResponseEntity<OrgDTO> getOrg(@PathVariable String orgId) throws NotFoundException {
        log.debug("Getting org for id: " + orgId);
        return ResponseEntity.ok(orgService.getOrgById(orgId));
    }

    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "Create a new org")
    @PostMapping("/")
    public ResponseEntity<OrgDTO> createOrg(@RequestBody OrgDTO orgDTO) {
        return ResponseEntity.ok(orgService.createOrg(orgDTO));
    }

    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "Replace an existing org")
    @PutMapping("/{orgId}/")
    public ResponseEntity<OrgDTO> replaceOrg(@PathVariable String orgId, @RequestBody OrgDTO orgDTO)
            throws NotFoundException {
        return ResponseEntity.ok(orgService.replaceOrg(orgId, orgDTO));
    }

    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "Update an existing org with specific fields")
    @PatchMapping("/{orgId}/")
    public ResponseEntity<OrgDTO> updateOrg(@PathVariable String orgId, @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String symbol, @RequestParam(required = false) String description,
                                            String leaderHandle, @RequestParam(required = false) String imageUrl,
                                            @RequestParam(required = false) String archeType, @RequestParam(required = false) String lang,
                                            @RequestParam(required = false) String commitment, @RequestParam(required = false) Boolean recruiting,
                                            @RequestParam(required = false) Boolean rolePlay, @RequestParam(required = false) Long members,
                                            @RequestParam(required = false) String uri) throws NotFoundException {
        return ResponseEntity.ok(orgService.updateOrg(orgId, name, symbol, description, leaderHandle, imageUrl,
                archeType, lang, commitment, recruiting, rolePlay, members, uri));
    }

    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "Dalete an org with org ID")
    @DeleteMapping("/{orgId}/")
    public ResponseEntity<Object> deleteOrg(@PathVariable String orgId) throws NotFoundException {
        orgService.deleteOrg(orgId);
        return ResponseEntity.accepted().build();
    }

}
