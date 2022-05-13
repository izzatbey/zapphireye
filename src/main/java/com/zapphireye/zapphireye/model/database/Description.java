package com.zapphireye.zapphireye.model.database;

import lombok.Data;

import java.util.List;

@Data
public class Description {
    private String desc;
    private String solution;
    private List<Instance> instances;
}
