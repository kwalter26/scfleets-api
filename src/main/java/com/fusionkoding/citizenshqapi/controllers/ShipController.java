package com.fusionkoding.citizenshqapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @GetMapping(value = "/")
    public ResponseEntity<List<ShipDTO>> getShips() {

        List<ShipDTO> ships = new ArrayList<>();
        ShipDTO ship = new ShipDTO();
        ship.setName("Mercury Star Runner");
        ships.add(ship);

        return ResponseEntity.ok(ships);
    }

}
