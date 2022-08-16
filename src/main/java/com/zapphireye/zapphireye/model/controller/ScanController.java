package com.zapphireye.zapphireye.model.controller;

import com.zapphireye.zapphireye.helper.scheduler.ScheduleScanTask;
import com.zapphireye.zapphireye.model.database.Description;
import com.zapphireye.zapphireye.model.database.assessment.*;
import com.zapphireye.zapphireye.model.database.scan.Scan;
import com.zapphireye.zapphireye.model.database.Url;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.model.response.ScanResponse;
import com.zapphireye.zapphireye.service.RiskService;
import com.zapphireye.zapphireye.service.ScanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ScanController {

    private RiskService riskService;
    private ScanService scanService;
    private TaskScheduler taskScheduler;

    @RequestMapping(method = RequestMethod.GET, path = "/api/scan")
    public List<Scan> fetchAllScans(){
        return scanService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/scan/desc")
    public Description fetchAllScans(@RequestParam(required = true) String id, @RequestParam(required = true) String pluginid){
        if(id == null || pluginid == null) {
            return null;
        } else {
            return scanService.findByParam(id, pluginid);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getUrl")
    public List<Url> fetchAllUrl() {
        return scanService.findUrlAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> startScan(@RequestBody(required = true) CreateScanRequest request){
        Url urlModel = new Url();
        Risk risk = new Risk();
        ThreatAgent threatAgent = new ThreatAgent();
        Vulnerability vulnerability = new Vulnerability();
        Technical technical = new Technical();
        Business business = new Business();

        urlModel.setName(request.getName());
        urlModel.setUrl(request.getUrl());
        urlModel.setOperator(request.getOperator());
        urlModel.setPeriod(request.getPeriod());
        urlModel.setDesc(request.getDescription());
        threatAgent.setSkill(request.getSkill());
        threatAgent.setMotive(request.getMotive());
        threatAgent.setOpportunity(request.getOpportunity());
        threatAgent.setPopulation(request.getPopulation());
        risk.setThreatAgent(threatAgent);
        vulnerability.setDiscovery(request.getDiscovery());
        vulnerability.setExploit(request.getExploit());
        vulnerability.setAwareness(request.getAwareness());
        vulnerability.setIntrusion(request.getIntrusion());
        risk.setVulnerability(vulnerability);
        technical.setConfidentality(request.getConfidentality());
        technical.setIntegrity(request.getIntegrity());
        technical.setAvailability(request.getAvailability());
        technical.setAccountability(request.getAccountability());
        risk.setTechnical(technical);
        business.setFinancial(request.getFinancial());
        business.setReputation(request.getReputation());
        business.setCompliance(request.getCompliance());
        business.setPrivacy(request.getPrivacy());
        risk.setBusiness(business);

        riskService.calculateRisk(risk);
        urlModel.setImpactLevel(risk.getImpactLevel());
        urlModel.setImpactRate(risk.getImpactRate());
        urlModel.setLikelihoodLevel(risk.getLikelihoodLevel());
        urlModel.setLikelihoodRate(risk.getLikelihoodRate());
        urlModel.setRiskLevel(risk.getRiskLevel());
        System.out.println("Url Model Likelihood : " + urlModel.getLikelihoodRate());
        System.out.println("Url Model Impact : " + urlModel.getImpactRate());

        scanService.saveUrlData(urlModel);

        int periodDays = 1;

        switch (request.getPeriod()) {
            case "Monthly":
                periodDays = 30;
                break;
            case "3Weeks":
                periodDays = 21;
                break;
            case "2Weeks":
                periodDays = 14;
                break;
            case "Weekly":
                periodDays = 7;
                break;
            case "3Months":
                periodDays = 90;
                break;
            case "Yearly":
                periodDays = 365;
                break;
            case "3Minutes":
                periodDays = 1;
                break;
        }

        taskScheduler.schedule(new ScheduleScanTask(urlModel.getUrl(), scanService), new PeriodicTrigger(periodDays, TimeUnit.MINUTES));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ScanResponse("Url " + request.getUrl() + " Added"));
    }


}
