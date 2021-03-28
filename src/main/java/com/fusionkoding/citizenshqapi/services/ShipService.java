package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.db.entities.Ship;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import com.fusionkoding.citizenshqapi.dtos.ShipDTO;

import java.util.List;

public interface ShipService {

    List<ShipDTO> getShips();

    ShipDTO getShipById(String shipId) throws NotFoundException;

    ShipDTO createShip(ShipDTO shipDto);

    ShipDTO replaceShip(String shipId, ShipDTO shipDto) throws NotFoundException;

    ShipDTO updateShip(String shipId, String name, String productionStatus, Double length, Double beam,
                       Double height, String size, Double mass, String type, Double cargoCapacity, Double minCrew, Double maxCrew,
                       Double scmSpeed, Double afterburnerSpeed, Double pitchMax, Double yawMax, Double rollMax,
                       Double xaxisAcceleration, Double yaxisAcceleration, Double zaxisAcceleration, String timeModified,
                       String focus, String description, String manufacturerCode, String manufacturerName, String imgUrl)
            throws NotFoundException;

    void deleteShip(String shipId) throws NotFoundException;

    ShipDTO convertToShipDto(Ship ship);

    Ship convertToShip(ShipDTO shipDto);
}
