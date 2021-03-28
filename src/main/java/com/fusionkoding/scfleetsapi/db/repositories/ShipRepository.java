package com.fusionkoding.scfleetsapi.db.repositories;

import com.fusionkoding.scfleetsapi.db.entities.Ship;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShipRepository extends MongoRepository<Ship, String> {
    public Optional<Ship> findByRsiId(Long rsiId);
}
