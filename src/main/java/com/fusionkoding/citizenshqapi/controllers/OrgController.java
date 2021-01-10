package com.fusionkoding.citizenshqapi.controllers;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.services.OrgService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orgs")
@Api(tags = { "Orgs" }, value = "Orgs", description = "Routes used for maintaining origization data.")
public class OrgController {

    private final OrgService orgService;

    @GetMapping("/")
    public List<OrgDTO> getOrgs() {
        log.debug("Getting all orgs");
        return orgService.getOrgs();
    }

}
