package com.zapphireye.zapphireye.model.controller;

import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.service.ScanService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/scan")
@AllArgsConstructor
public class ScanController {

    private ScanService scanService;

    @GetMapping
    public List<Scan> fetchAllScans(){
        return scanService.findAll();
    }

    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Scan startScan(@RequestBody(required = true) CreateScanRequest request){
        try {
            return scanService.startFullScan(request);
        } catch (ClientApiException e) {
            e.printStackTrace();
        }
        return null;
    }



}
