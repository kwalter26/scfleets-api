package com.fusionkoding.citizenshqapi.db.repositories;

import com.fusionkoding.citizenshqapi.db.entities.Pilot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PilotRepository extends MongoRepository<Pilot, String> {
    public Optional<Pilot> findByUserName(String userName);

}
