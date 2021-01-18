package com.fusionkoding.citizenshqapi.services;

import java.util.Timer;
import java.util.stream.Collectors;

import com.fusionkoding.citizenshqapi.client.RsiSiteClient;
import com.fusionkoding.citizenshqapi.client.models.ShipMatrixResponse;
import com.fusionkoding.citizenshqapi.client.models.ShipResponse;
import com.fusionkoding.citizenshqapi.dtos.ShipDTO;
import com.fusionkoding.citizenshqapi.jobs.OrgTask;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    private final RsiSiteClient rsiSiteClient;
    private final OrgService orgService;
    private final ShipService shipService;
    private final ModelMapper modelMapper;

    @Override
    public void reloadShips() {
        ShipMatrixResponse response = rsiSiteClient.getShipMatrix();
        response.getData().stream().map(ship -> shipService.createShip(convertShipResponsetoDto(ship)))
                .collect(Collectors.toList());

    }

    @Override
    public void reloadOrgs(Long interval) {
        log.info("Reloading Orgs on interval: 32 orgs per " + interval + " sec");
        Timer timer = new Timer();
        if (interval == null) {
            interval = 600L;
        }
        timer.schedule(new OrgTask(rsiSiteClient, orgService, timer), 0, interval);
        log.debug("Org reload started");
    }

    private ShipDTO convertShipResponsetoDto(ShipResponse shipResponse) {
        ShipDTO shipDto = modelMapper.map(shipResponse, ShipDTO.class);
        String smallImage = shipResponse.getMedia().get(0).getImages().getSmall();
        if (!smallImage.contains("https://")) {
            smallImage = "https://robertsspaceindustries.com" + smallImage;
        }
        shipDto.setImgUrl(smallImage);
        return shipDto;
    }

}
