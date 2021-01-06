package com.fusionkoding.citizenshqapi.controllers;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.services.ShipService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<ShipDTO> getShip(@PathVariable String shipId) throws NotFoundException {
        return ResponseEntity.ok(shipService.getShipById(shipId));
    }

    @PostMapping("/")
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO) {
        return ResponseEntity.ok(shipService.createShip(shipDTO));
    }

    @PutMapping("/{shipId}/")
    public ResponseEntity<ShipDTO> replaceShip(@PathVariable String shipId, @RequestBody ShipDTO shipDTO)
            throws NotFoundException {
        return ResponseEntity.ok(shipService.replaceShip(shipId, shipDTO));
    }

    @PatchMapping("/{shipId}/")
    public ResponseEntity<ShipDTO> updateShip(@PathVariable String shipId, @RequestParam String name,
            @RequestParam(required = false) String productionStatus, @RequestParam(required = false) Double length,
            @RequestParam(required = false) Double beam, @RequestParam(required = false) Double height,
            @RequestParam(required = false) String size, @RequestParam(required = false) Double mass,
            @RequestParam(required = false) String type, @RequestParam(required = false) Double cargocapacity,
            @RequestParam(required = false) Double minCrew, @RequestParam(required = false) Double maxCrew,
            @RequestParam(required = false) Double scmSpeed, @RequestParam(required = false) Double afterburnerSpeed,
            @RequestParam(required = false) Double pitchMax, @RequestParam(required = false) Double yawMax,
            @RequestParam(required = false) Double rollMax, @RequestParam(required = false) Double xaxisAcceleration,
            @RequestParam(required = false) Double yaxisAcceleration,
            @RequestParam(required = false) Double zaxisAcceleration,
            @RequestParam(required = false) String timeModified, @RequestParam(required = false) String focus,
            @RequestParam(required = false) String description, @RequestParam(required = false) String manufacturerCode,
            @RequestParam(required = false) String manufacturerName, @RequestParam(required = false) String imgUrl)
            throws NotFoundException {
        return ResponseEntity.ok(shipService.updateShip(shipId, name, productionStatus, length, beam, height, size,
                mass, type, cargocapacity, minCrew, maxCrew, scmSpeed, afterburnerSpeed, pitchMax, yawMax, rollMax,
                xaxisAcceleration, yaxisAcceleration, zaxisAcceleration, timeModified, focus, description,
                manufacturerCode, manufacturerName, imgUrl));
    }

    @DeleteMapping("/{shipId}/")
    public ResponseEntity<ShipDTO> deleteShip(@PathVariable String shipId) throws NotFoundException {
        shipService.deleteShip(shipId);
        return ResponseEntity.accepted().build();
    }

}
