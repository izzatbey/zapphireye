package com.zapphireye.zapphireye.model.request;

public class CreateScanRequest {
    private String driverPath;
    private String url;

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
