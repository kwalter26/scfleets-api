package com.fusionkoding.scfleetsapi.db.repositories;

import com.fusionkoding.scfleetsapi.db.entities.Org;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrgRepository extends MongoRepository<Org, String> {
    public Optional<Org> findBySymbol(String symbol);
}
