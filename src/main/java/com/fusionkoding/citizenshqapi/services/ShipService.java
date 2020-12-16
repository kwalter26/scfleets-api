package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.controllers.ShipDTO;

public interface ShipService {

    public List<ShipDTO> getShips();

    public ShipDTO getShipById(String shipId);
}
