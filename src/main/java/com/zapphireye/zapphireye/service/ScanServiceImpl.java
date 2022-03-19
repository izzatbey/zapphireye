package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.helper.AutomateZap;
import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.repository.ScanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApiException;

import java.util.List;

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
    public Scan findByParam(String param) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
