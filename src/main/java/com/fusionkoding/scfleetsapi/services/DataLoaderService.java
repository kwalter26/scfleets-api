package com.fusionkoding.scfleetsapi.services;

public interface DataLoaderService {
    public void reloadShips();

    public void reloadOrgs(Long interval);
}
