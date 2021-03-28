package com.fusionkoding.citizenshqapi.db.repositories;

import com.fusionkoding.citizenshqapi.db.entities.Ship;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShipRepository extends MongoRepository<Ship, String> {
    public Optional<Ship> findByRsiId(Long rsiId);
}
