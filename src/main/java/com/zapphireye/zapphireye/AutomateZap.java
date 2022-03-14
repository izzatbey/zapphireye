package com.zapphireye.zapphireye;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.zaproxy.clientapi.core.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AutomateZap {
    Logger log = Logger.getLogger(AutomateZap.class.getName());
    String driverPath = "/home/izzatbey/Documents/zapphireye/drivers/chromedriver";
    String url = "http://localhost:3000";
    String host = "localhost";
    int port = 8098;
    private final ClientApi clientapi;
    private static List<String> blackListPlugins = Arrays.asList("1000", "1025");

    public AutomateZap() throws ClientApiException {
        this.clientapi = new ClientApi(host, port);
        setup();
        spider();
        activeScan();
        result();
    }

    private void setup() throws ClientApiException {
        clientapi.selenium.setOptionChromeDriverPath(driverPath);
        log.info("Driver Created");
    }

    public static String convertObjToString(Object clsObj) {
        String jsonSender = new Gson().toJson(clsObj, new TypeToken<Object>() {
        }.getType());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(clsObj, new TypeToken<Object>() {
        }.getType());
        return prettyJson;
    }

    private void spider() {
        String TARGET = "http://localhost:3000";
        try {
            // Start spidering the target
            log.info("Spidering target : " + TARGET);
            ApiResponse resp = clientapi.spider.scan(TARGET, null, null, null, null);
            String scanID;
            int progress;

            // The scan returns a scan id to support concurrent scanning
            scanID = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) clientapi.spider.status(scanID)).getValue());
                log.info("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            log.info("Spider completed");
            // If required post process the spider results
            List<ApiResponse> spiderResults = ((ApiResponseList) clientapi.spider.results(scanID)).getItems();
            System.out.println(convertObjToString(spiderResults));

//            List<String> urlResult = new ArrayList<>();
//            for (ApiResponse response:spiderResults) {
//                urlResult.add(response.)
//            }
            // TODO: Explore the Application more with Ajax Spider or Start scanning the application for vulnerabilities

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ajaxSpider() {

    }

    private void activeScan() {
        String TARGET = "http://localhost:3000";

        try {
            // TODO : explore the app (Spider, etc) before using the Active Scan API, Refer the explore section
            log.info("Active Scanning target : " + TARGET);
            ApiResponse resp = clientapi.ascan.scan(TARGET, "True", "False", null, null, null);
            String scanid;
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) clientapi.ascan.status(scanid)).getValue());
                log.info("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }

            log.info("Active Scan complete");
            // Print vulnerabilities found by the scanning
            log.info("Alerts:");
            log.info(new String(clientapi.core.jsonreport(), StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void result() {
        String TARGET = "http://localhost:3000";

        try {
            // TODO: Check if the scanning has completed

            // Retrieve the alerts using paging in case there are lots of them
            int start = 0;
            int count = 5000;
            int alertCount = 0;
            ApiResponse resp = clientapi.alert.alerts(TARGET, String.valueOf(start), String.valueOf(count), null);

            while (((ApiResponseList) resp).getItems().size() != 0) {
                System.out.println("Reading " + count + " alerts from " + start);
                alertCount += ((ApiResponseList) resp).getItems().size();

                for (ApiResponse l : (((ApiResponseList) resp).getItems())) {

                    Map<String, ApiResponse> element = ((ApiResponseSet) l).getValuesMap();
                    if (blackListPlugins.contains(element.get("pluginId").toString())) {
                        // TODO: Trigger any relevant postprocessing
                    } else if ("High".equals(element.get("risk").toString())) {
                        // TODO: Trigger any relevant postprocessing
                    } else if ("Informational".equals(element.get("risk").toString())) {
                        // TODO: Ignore all info alerts - some of them may have been downgraded by security annotations
                    }
                }
                start += count;
                resp = clientapi.alert.alerts(TARGET, String.valueOf(start), String.valueOf(count), null);
            }
            System.out.println("Total number of Alerts: " + alertCount);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}