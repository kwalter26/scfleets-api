package com.fusionkoding.citizenshqapi.repositories;

import java.util.Optional;

import com.fusionkoding.citizenshqapi.entities.Pilot;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PilotRepository extends MongoRepository<Pilot, String> {
    public Optional<Pilot> findByUserName(String userName);

}
