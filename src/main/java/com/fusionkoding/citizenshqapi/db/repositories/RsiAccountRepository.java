package com.fusionkoding.citizenshqapi.db.repositories;

import com.fusionkoding.citizenshqapi.db.entities.RsiAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RsiAccountRepository extends MongoRepository<RsiAccount, String> {
}
