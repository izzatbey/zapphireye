package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.Description;
import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import org.zaproxy.clientapi.core.ClientApiException;

import java.util.List;

public interface ScanService {

    Scan startFullScan(CreateScanRequest request) throws ClientApiException;

    void update(String id);

    List<Scan> findAll();

    Description findByParam(String id, String pluginid);

    Scan getDescById(String id);

    void delete(String id);
}
