package com.fusionkoding.citizenshqapi.controllers;

import com.fusionkoding.citizenshqapi.services.DataLoaderService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/loader/")
@Controller
public class DataLoaderController {

    private final DataLoaderService dataLoaderService;

    @GetMapping("/ships/")
    public ResponseEntity<Object> reloadShips() {
        dataLoaderService.reloadShips();
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/orgs/")
    public ResponseEntity<Object> reloadOrgs(@PathVariable(required = false) Long interval) {
        dataLoaderService.reloadOrgs(interval);
        return ResponseEntity.accepted().build();
    }

}
