package com.zapphireye.zapphireye.model.database;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Site {
    @SerializedName(value = "@name")
    private String domain;
    @SerializedName(value = "@host")
    private String host;
    @SerializedName(value = "@port")
    private String port;
    @SerializedName(value = "@ssl")
    private String ssl;
    private List<Alert> alerts;
}
