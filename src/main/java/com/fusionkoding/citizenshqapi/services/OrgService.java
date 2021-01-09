package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.dao.DuplicateKeyException;

public interface OrgService {

    public List<OrgDTO> getOrgs();

    public OrgDTO getOrgById(String orgId) throws NotFoundException;

    public OrgDTO getOrgBySymbol(String symbol) throws NotFoundException;

    public OrgDTO createOrg(OrgDTO orgDTO) throws DuplicateKeyException;

    public OrgDTO replaceOrg(String orgId, OrgDTO orgDTO) throws NotFoundException;

    public OrgDTO updateOrg(String orgId, String name, String symbol, String description, String leaderHandle,
            String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
            Long members) throws NotFoundException;

    public OrgDTO updateOrgWithSymbol(String name, String symbol, String description, String leaderHandle,
            String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
            Long members) throws NotFoundException;

    public void deleteOrg(String orgId) throws NotFoundException;

    public List<OrgDTO> reloadOrgs();

}
