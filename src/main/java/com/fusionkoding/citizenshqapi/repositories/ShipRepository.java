package com.fusionkoding.citizenshqapi.repositories;

import com.fusionkoding.citizenshqapi.entities.Ship;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipRepository extends MongoRepository<Ship, String> {
}
