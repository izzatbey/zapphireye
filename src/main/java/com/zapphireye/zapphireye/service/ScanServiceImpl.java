package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.helper.AutomateZap;
import com.zapphireye.zapphireye.model.database.Alert;
import com.zapphireye.zapphireye.model.database.Description;
import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.repository.ScanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApiException;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScanServiceImpl implements ScanService{

    @Autowired
    private ScanRepository scanRepository;

    @Override
    public Scan startFullScan(CreateScanRequest request) throws ClientApiException {
        AutomateZap autoZap = new AutomateZap(request.getDriverPath(), request.getUrl());
        autoZap.setup();
        autoZap.spider();
        Scan result = autoZap.activeScan();
        return scanRepository.save(result);
    }

    @Override
    public void update(String id) {

    }

    @Override
    public List<Scan> findAll() {
        return scanRepository.findAll();
    }

    @Override
    public Description findByParam(String id, String pluginid) {

        Optional<Scan> scan = scanRepository.findById(id);
        if(scan.isEmpty()) {
            return null;
        } else {
            List<Alert> alerts = scan.get().getSite().getAlerts();
            for (int i = 0; i < alerts.size(); i++) {
                if(alerts.get(i).getPluginid().equals(pluginid)) {
                    Description description = new Description();
                    description.setDesc(alerts.get(i).getDesc());
                    description.setSolution(alerts.get(i).getSolution());
                    description.setInstances(alerts.get(i).getInstances());
                    return description;
                }
            }
        }
        return null;
    }

    @Override
    public Scan getDescById(String id) {
            return null;
    }

    @Override
    public void delete(String id) {

    }
}
