package com.fusionkoding.citizenshqapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.fusionkoding.citizenshqapi.client.RsiSiteClient;
import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.jobs.OrgTask;
import com.fusionkoding.citizenshqapi.services.OrgService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orgs")
public class OrgController {

    private final OrgService orgService;
    private final RsiSiteClient rsiSiteClient;

    @GetMapping("/")
    public List<OrgDTO> getOrgs() {
        log.debug("Getting all orgs");
        return orgService.getOrgs();
    }

    @GetMapping("/reload/")
    public ResponseEntity<Object> reloadOrgs(@PathVariable(required = false) Long interval) {
        log.debug("Reloading Orgs");
        Timer timer = new Timer();
        if (interval == null) {
            interval = 600L;
        }
        timer.schedule(new OrgTask(rsiSiteClient, orgService, timer), 0, interval);
        log.info("HERE---------------------");
        return ResponseEntity.accepted().build();
    }

}
