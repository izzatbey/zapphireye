package com.zapphireye.zapphireye.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String pluginid;
    private String name;
    private String riskdesc;
    private String desc;
    private String count;
    private String solution;
    private String otherinfo;
    private String reference;
    private String cweid;
    private String wascid;
    private List<Instance> instances;
}