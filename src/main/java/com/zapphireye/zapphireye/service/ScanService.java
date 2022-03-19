package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import org.zaproxy.clientapi.core.ClientApiException;

import java.util.List;

public interface ScanService {

    Scan startFullScan(CreateScanRequest request) throws ClientApiException;

    void update(String id);

    List<Scan> findAll();

    Scan findByParam(String param);

    void delete(String id);
}
