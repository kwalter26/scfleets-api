package com.fusionkoding.citizenshqapi.repositories;

import com.fusionkoding.citizenshqapi.entities.RsiAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RsiAccountRepository extends MongoRepository<RsiAccount, String> {
}
