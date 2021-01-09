package com.fusionkoding.citizenshqapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.entities.Org;
import com.fusionkoding.citizenshqapi.entities.Ship;
import com.fusionkoding.citizenshqapi.repositories.OrgRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.modelmapper.ModelMapper;
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
    public OrgDTO createOrg(OrgDTO orgDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrgDTO replaceOrg(String orgId, OrgDTO orgDTO) throws NotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrgDTO updateOrg(String orgId, String name, String symbol, String description, String leaderHandle,
            String imageUrl, String archeType, String lang, String commitment, String recruiting, String rolePlay,
            long members) throws NotFoundException {
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

    private Ship convertToEntity(ShipDTO shipDto) {
        return modelMapper.map(shipDto, Ship.class);
    }

}
