package com.fusionkoding.scfleetsapi.db.repositories;

import com.fusionkoding.scfleetsapi.db.entities.Pilot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PilotRepository extends MongoRepository<Pilot, String> {
    public Optional<Pilot> findByUserName(String userName);

}
