package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.Description;
import com.zapphireye.zapphireye.model.database.scan.Scan;
import com.zapphireye.zapphireye.model.database.Url;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import org.zaproxy.clientapi.core.ClientApiException;

import java.util.List;

public interface ScanService {

    Scan startFullScan(String request) throws ClientApiException;

    Url saveUrlData(Url url);
    List<Url> findUrlAll();
    void update(String id);

    List<Scan> findAll();

    Description findByParam(String id, String pluginid);

    Scan getDescById(String id);

    void delete(String id);
}
