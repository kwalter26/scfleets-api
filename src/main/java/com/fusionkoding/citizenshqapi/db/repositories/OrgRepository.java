package com.fusionkoding.citizenshqapi.db.repositories;

import java.util.Optional;

import com.fusionkoding.citizenshqapi.db.entities.Org;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgRepository extends MongoRepository<Org, String> {
    public Optional<Org> findBySymbol(String symbol);
}
