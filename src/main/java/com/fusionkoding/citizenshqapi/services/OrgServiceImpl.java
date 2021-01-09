package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.client.RsiSiteClient;
import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.entities.Org;
import com.fusionkoding.citizenshqapi.repositories.OrgRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrgServiceImpl implements OrgService {

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Org Not Found");
    private final OrgRepository orgRepository;
    private final ModelMapper modelMapper;
    private final RsiSiteClient rsiSiteClient;

    @Override
    public List<OrgDTO> getOrgs() {
        log.debug("Getting all orgs");
        List<Org> orgs = orgRepository.findAll();
        log.debug("Retrieved all orgs: size " + orgs.size());
        return orgs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public OrgDTO getOrgById(String orgId) throws NotFoundException {
        log.debug("Getting org for id: " + orgId);
        Org org = orgRepository.findById(orgId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        log.debug("Found org " + org.getName() + " for id: " + orgId);
        return convertToDto(org);
    }

    @Override
    public OrgDTO getOrgBySymbol(String symbol) throws NotFoundException {
        log.debug("Getting org for symbol: " + symbol);
        Org org = orgRepository.findBySymbol(symbol).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        log.debug("Found org " + org.getName() + " for symbol: " + symbol);
        return convertToDto(org);
    }

    @Override
    public OrgDTO createOrg(OrgDTO orgDTO) throws DuplicateKeyException {
        log.debug("Creating org for: " + orgDTO.getName());
        Org org = orgRepository.save(convertToEntity(orgDTO));
        log.debug("Created org for: " + orgDTO.getName());
        return convertToDto(org);
    }

    @Override
    public OrgDTO replaceOrg(String orgId, OrgDTO orgDTO) throws NotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrgDTO updateOrgWithSymbol(String symbol, String name, String description, String leaderHandle,
            String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
            Long members) throws NotFoundException {
        log.info("Updating org for: " + name);
        Org org = orgRepository.findBySymbol(symbol).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        updateOrParameters(name, description, leaderHandle, imageUrl, archeType, lang, commitment, recruiting, rolePlay,
                members, org);
        OrgDTO orgDTO = convertToDto(orgRepository.save(org));
        log.info("Updated org for: " + name);
        return orgDTO;
    }

    @Override
    public OrgDTO updateOrg(String orgId, String symbol, String name, String description, String leaderHandle,
            String imageUrl, String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay,
            Long members) throws NotFoundException {
        Org org;
        log.debug("Updating org for: " + name);

        try {
            org = orgRepository.findById(orgId).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        } catch (Exception e) {
            org = orgRepository.findBySymbol(symbol).orElseThrow(() -> NOT_FOUND_EXCEPTION);
        }
        updateOrParameters(name, description, leaderHandle, imageUrl, archeType, lang, commitment, recruiting, rolePlay,
                members, org);
        OrgDTO orgDTO = convertToDto(orgRepository.save(org));
        log.debug("Updated org for: " + name);
        return orgDTO;
    }

    private void updateOrParameters(String name, String description, String leaderHandle, String imageUrl,
            String archeType, String lang, String commitment, Boolean recruiting, Boolean rolePlay, Long members,
            Org org) {
        if (name != null) {
            org.setName(name);
        }
        if (description != null) {
            org.setDescription(description);
        }
        if (leaderHandle != null) {
            org.setLeaderHandle(leaderHandle);
        }
        if (imageUrl != null) {
            org.setImageUrl(imageUrl);
        }
        if (archeType != null) {
            org.setArcheType(archeType);
        }
        if (lang != null) {
            org.setLang(lang);
        }
        if (commitment != null) {
            org.setCommitment(commitment);
        }
        if (recruiting != null) {
            org.setRecruiting(recruiting);
        }
        if (rolePlay != null) {
            org.setRolePlay(rolePlay);
        }
        if (members != null) {
            org.setMembers(members);
        }
    }

    @Override
    public List<OrgDTO> reloadOrgs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteOrg(String orgId) throws NotFoundException {
        // TODO Auto-generated method stub

    }

    private OrgDTO convertToDto(Org org) {
        return modelMapper.map(org, OrgDTO.class);
    }

    private Org convertToEntity(OrgDTO orgDTO) {
        return modelMapper.map(orgDTO, Org.class);
    }

}
