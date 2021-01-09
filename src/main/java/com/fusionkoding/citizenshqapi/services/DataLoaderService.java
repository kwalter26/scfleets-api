package com.fusionkoding.citizenshqapi.services;

public interface DataLoaderService {
    public void reloadShips();

    public void reloadOrgs(Long interval);
}
