package com.zapphireye.zapphireye.model.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Url {
    @Id
    private String id;
    private String name;
    private String url;
    private String operator;
    private String period;
    private String desc;
    private float impactRate;
    private String impactLevel;
    private float likelihoodRate;
    private String likelihoodLevel;
    private String RiskLevel;
}
