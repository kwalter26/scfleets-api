package com.fusionkoding.citizenshqapi.repositories;

import com.fusionkoding.citizenshqapi.entities.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SettingsRepository extends MongoRepository<Setting,String> {
    Optional<Setting> findByName(String name);
}
