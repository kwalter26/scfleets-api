package com.fusionkoding.citizenshqapi.repositories;

import com.fusionkoding.citizenshqapi.entities.Org;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgRepository extends MongoRepository<Org, String> {
}
