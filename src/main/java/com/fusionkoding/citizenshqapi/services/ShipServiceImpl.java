package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.controllers.ShipDTO;
import com.fusionkoding.citizenshqapi.entities.Ship;
import com.fusionkoding.citizenshqapi.repositories.ShipRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;

    @Override
    public List<ShipDTO> getShips() {
        List<Ship> ships = shipRepository.findAll();

        return ships.stream().map(ship -> ShipDTO.builder().name(ship.getName()).id(ship.getId()).build())
                .collect(Collectors.toList());
    }

    @Override
    public ShipDTO getShipById(String shipId) {
        ShipDTO ship = new ShipDTO();
        ship.setId(shipId);
        ship.setName("Mercury Star Runner");
        return ship;
    }

}
