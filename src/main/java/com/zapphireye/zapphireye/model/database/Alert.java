package com.zapphireye.zapphireye.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String pluginId;
    private String name;
    private String risk;
    private String desc;
    private String count;
    private String solution;
    private String reference;
    private String cweId;
}
