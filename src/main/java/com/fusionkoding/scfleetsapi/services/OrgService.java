package com.fusionkoding.scfleetsapi.services;

import com.fusionkoding.scfleetsapi.dtos.OrgDTO;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface OrgService {

    public List<OrgDTO> getOrgs();

    public OrgDTO getOrgById(String orgId) throws NotFoundException;

    public OrgDTO getOrgBySymbol(String symbol) throws NotFoundException;

    public OrgDTO createOrg(OrgDTO orgDTO) throws DuplicateKeyException;

    public OrgDTO replaceOrg(String orgId, OrgDTO orgDTO) throws NotFoundException;

    public OrgDTO updateOrg(String orgId, String name, String symbol, String description, String leaderHandle,
                            String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
                            Long members, String uri) throws NotFoundException;

    public OrgDTO updateOrgWithSymbol(String name, String symbol, String description, String leaderHandle,
                                      String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
                                      Long members, String uri) throws NotFoundException;

    public void deleteOrg(String orgId) throws NotFoundException;

}
