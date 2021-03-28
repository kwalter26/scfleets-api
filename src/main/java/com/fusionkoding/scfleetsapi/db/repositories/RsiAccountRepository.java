package com.fusionkoding.scfleetsapi.db.repositories;

import com.fusionkoding.scfleetsapi.db.entities.RsiAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RsiAccountRepository extends MongoRepository<RsiAccount, String> {
}
