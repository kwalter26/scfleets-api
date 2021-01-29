package com.fusionkoding.citizenshqapi.repositories;

import com.fusionkoding.citizenshqapi.entities.Pilot;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PilotRepository extends MongoRepository<Pilot, String> {
  
}
