package com.fusionkoding.citizenshqapi.controllers;

import java.util.List;

import com.fusionkoding.citizenshqapi.services.ShipService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ships")
public class ShipController {

    public final ShipService shipService;

    @GetMapping("/")
    public ResponseEntity<List<ShipDTO>> getShips() {
        return ResponseEntity.ok(shipService.getShips());
    }

    @GetMapping("/{shipId}/")
    public ResponseEntity<ShipDTO> getShip(@PathVariable String shipId) {
        return ResponseEntity.ok(shipService.getShipById(shipId));
    }

}
