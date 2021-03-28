package com.fusionkoding.citizenshqapi.db.repositories;

import java.util.Optional;

import com.fusionkoding.citizenshqapi.db.entities.Ship;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipRepository extends MongoRepository<Ship, String> {
    public Optional<Ship> findByRsiId(Long rsiId);
}
