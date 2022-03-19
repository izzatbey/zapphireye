package com.zapphireye.zapphireye.helper.scheduler;

import com.zapphireye.zapphireye.model.request.CreateScanRequest;
import com.zapphireye.zapphireye.service.ScanService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zaproxy.clientapi.core.ClientApiException;

public class ScheduleScanTask implements Runnable{

    private CreateScanRequest request;
    private ScanService scanService;

    public ScheduleScanTask(CreateScanRequest request, ScanService scanService) {
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
