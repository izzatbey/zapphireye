package com.zapphireye.zapphireye.helper.scheduler;

import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.service.ScanService;
import org.zaproxy.clientapi.core.ClientApiException;

public class ScheduleScanTask implements Runnable{

    private String request;
    private ScanService scanService;

    public ScheduleScanTask(String request, ScanService scanService) {
        this.request = request;
        this.scanService = scanService;
    }

    @Override
    public void run() {
        try {
            System.out.println("Scan Running");
            scanService.startFullScan(request);
        } catch (ClientApiException e) {
            e.printStackTrace();
        }
    }
}
