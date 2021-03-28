package com.fusionkoding.citizenshqapi.db.repositories;

import com.fusionkoding.citizenshqapi.db.entities.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SettingsRepository extends MongoRepository<Setting,String> {
    Optional<Setting> findByName(String name);
}
