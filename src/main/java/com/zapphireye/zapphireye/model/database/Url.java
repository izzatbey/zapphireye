package com.zapphireye.zapphireye.model.database;

import com.zapphireye.zapphireye.model.database.assessment.Risk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private float riskNumber;
    private String riskDesc;
}
