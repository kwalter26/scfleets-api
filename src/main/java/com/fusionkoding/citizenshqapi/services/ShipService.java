package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.client.models.ShipResponse;
import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

public interface ShipService {

    public List<ShipDTO> getShips();

    public ShipDTO getShipById(String shipId) throws NotFoundException;

    public ShipDTO createShip(ShipDTO shipDto);

    public ShipDTO replaceShip(String shipId, ShipDTO shipDto) throws NotFoundException;

    public ShipDTO updateShip(String shipId, String name, String productionStatus, Double length, Double beam,
            Double height, String size, Double mass, String type, Double cargoCapacity, Double minCrew, Double maxCrew,
            Double scmSpeed, Double afterburnerSpeed, Double pitchMax, Double yawMax, Double rollMax,
            Double xaxisAcceleration, Double yaxisAcceleration, Double zaxisAcceleration, String timeModified,
            String focus, String description, String manufacturerCode, String manufacturerName, String imgUrl)
            throws NotFoundException;

    public void deleteShip(String shipId) throws NotFoundException;

    public List<ShipDTO> reloadShip();

}
