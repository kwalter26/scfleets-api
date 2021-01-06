package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.entities.Ship;
import com.fusionkoding.citizenshqapi.repositories.ShipRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ShipDTO> getShips() {
        List<Ship> ships = shipRepository.findAll();

        return ships.stream().map(this::convertToShipDto).collect(Collectors.toList());
    }

    @Override
    public ShipDTO getShipById(String shipId) throws NotFoundException {
        return convertToShipDto(
                shipRepository.findById(shipId).orElseThrow(() -> new NotFoundException("Ship Not Found")));
    }

    @Override
    public ShipDTO createShip(ShipDTO shipDto) {
        Ship newShip = shipRepository.save(convertToShip(shipDto));
        return convertToShipDto(newShip);
    }

    @Override
    public ShipDTO updateShip(String shipId, String name, String productionStatus, Double length, Double beam,
            Double height, String size, Double mass, String type, Double cargocapacity, Double minCrew, Double maxCrew,
            Double scmSpeed, Double afterburnerSpeed, Double pitchMax, Double yawMax, Double rollMax,
            Double xaxisAcceleration, Double yaxisAcceleration, Double zaxisAcceleration, String timeModified,
            String focus, String description, String manufacturerCode, String manufacturerName, String imgUrl)
            throws NotFoundException {
        Ship ship = shipRepository.findById(shipId).orElseThrow(() -> new NotFoundException("Ship Not Found"));
        if (name != null)
            ship.setName(name);
        if (productionStatus != null)
            ship.setProductionStatus(productionStatus);
        if (length != null)
            ship.setLength(length);
        if (beam != null)
            ship.setBeam(beam);
        if (height != null)
            ship.setHeight(height);
        if (size != null)
            ship.setSize(size);
        if (mass != null)
            ship.setMass(mass);
        if (type != null)
            ship.setType(type);
        if (cargocapacity != null)
            ship.setCargocapacity(cargocapacity);
        if (minCrew != null)
            ship.setMinCrew(minCrew);
        if (maxCrew != null)
            ship.setMaxCrew(maxCrew);
        if (scmSpeed != null)
            ship.setScmSpeed(scmSpeed);
        if (afterburnerSpeed != null)
            ship.setAfterburnerSpeed(afterburnerSpeed);
        if (pitchMax != null)
            ship.setPitchMax(pitchMax);
        if (yawMax != null)
            ship.setYawMax(yawMax);
        if (rollMax != null)
            ship.setRollMax(rollMax);
        if (xaxisAcceleration != null)
            ship.setXaxisAcceleration(xaxisAcceleration);
        if (yaxisAcceleration != null)
            ship.setYaxisAcceleration(yaxisAcceleration);
        if (zaxisAcceleration != null)
            ship.setZaxisAcceleration(zaxisAcceleration);
        if (timeModified != null)
            ship.setTimeModified(timeModified);
        if (focus != null)
            ship.setFocus(focus);
        if (description != null)
            ship.setDescription(description);
        if (manufacturerCode != null)
            ship.setManufacturerCode(manufacturerCode);
        if (manufacturerName != null)
            ship.setManufacturerName(manufacturerName);
        if (imgUrl != null)
            ship.setImgUrl(imgUrl);
        return convertToShipDto(shipRepository.save(ship));
    }

    @Override
    public ShipDTO replaceShip(String shipId, ShipDTO shipDto) throws NotFoundException {
        Ship ship = shipRepository.findById(shipId).orElseThrow(() -> new NotFoundException("Ship Not Found"));
        shipDto.setId(ship.getId());
        return createShip(shipDto);
    }

    @Override
    public void deleteShip(String shipId) throws NotFoundException {
        Ship ship = shipRepository.findById(shipId).orElseThrow(() -> new NotFoundException("Ship Not Found"));
        shipRepository.delete(ship);
    }

    private ShipDTO convertToShipDto(Ship ship) {
        return modelMapper.map(ship, ShipDTO.class);
    }

    private Ship convertToShip(ShipDTO shipDto) {
        return modelMapper.map(shipDto, Ship.class);
    }

}
