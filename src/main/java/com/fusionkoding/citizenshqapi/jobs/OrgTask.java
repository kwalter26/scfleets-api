package com.fusionkoding.citizenshqapi.jobs;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusionkoding.citizenshqapi.client.RsiSiteClient;
import com.fusionkoding.citizenshqapi.client.models.OrgData;
import com.fusionkoding.citizenshqapi.client.models.OrgResponse;
import com.fusionkoding.citizenshqapi.dtos.OrgDTO;
import com.fusionkoding.citizenshqapi.services.OrgService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrgTask extends TimerTask {
    private long pageNumber = 1;
    private RsiSiteClient rsiSiteClient;
    private OrgService orgService;
    ObjectMapper mapper;
    List<OrgDTO> orgs;
    Timer timer;

    public OrgTask(RsiSiteClient rsiSiteClient, OrgService orgService, Timer timer) {
        this.rsiSiteClient = rsiSiteClient;
        this.mapper = new ObjectMapper();
        this.timer = timer;
        this.orgService = orgService;
    }

    @Override
    public void run() {

        log.info("Fetching page ---- " + pageNumber);

        OrgResponse response = rsiSiteClient.getOrgs(pageNumber);

        OrgData orgData = response.getData();
        if (orgData == null) {
            log.info("No Data Returned for page" + pageNumber);
            timer.cancel();
        }
        Document doc = Jsoup.parse(orgData.getHtml());
        Elements elements = doc.getElementsByClass("org-cell");

        if (elements.isEmpty()) {
            log.info("No Elements Returned for page" + pageNumber);
            timer.cancel();
        }

        for (Element element : elements) {
            OrgDTO orgDTO = parseOrg(element);
            try {
                orgService.updateOrgWithSymbol(orgDTO.getSymbol(), orgDTO.getName(), null, null, orgDTO.getImageUrl(),
                        orgDTO.getArcheType(), orgDTO.getLang(), orgDTO.getCommitment(), orgDTO.getRecruiting(),
                        orgDTO.getRolePlay(), orgDTO.getMembers(), orgDTO.getUri());
            } catch (NotFoundException e) {
                orgService.createOrg(orgDTO);
            }
        }
        pageNumber++;
    }

    private OrgDTO parseOrg(Element element) {
        String name = element.select("h3.name").first().text();
        String uri = element.select("a.clearfix").first().attr("href");
        String symbol = element.select("span.symbol").first().text();
        String lang = element.select("span.right > span:nth-child(1) > span:nth-child(2) > span.value").first().text();
        String archetype = element.select("span.right > span:nth-child(1) > span:nth-child(1) > span.value").first()
                .text();
        String commitment = element.select("span.right > span:nth-child(1) > span:nth-child(3) > span.value").first()
                .text();
        String imageUrl = element.select("span > img").first().attr("src");
        Boolean recruiting = element.select("span.right > span:nth-child(2) > span:nth-child(1) > span.value").first()
                .text().equals("Yes");
        Boolean rolePlay = element.select("span.right > span:nth-child(2) > span:nth-child(2) > span.value").first()
                .text().equals("Yes");
        Long members = 0L;
        try {
            members = Long.parseLong(
                    element.select("span.right > span:nth-child(2) > span:nth-child(3) > span.value").first().text());
        } catch (NumberFormatException e) {
            log.error("Members was not a Long value", e);
        }
        return OrgDTO.builder().name(name).symbol(symbol).imageUrl(imageUrl).archeType(archetype).lang(lang)
                .commitment(commitment).recruiting(recruiting).rolePlay(rolePlay).members(members).uri(uri).build();
    }

}
