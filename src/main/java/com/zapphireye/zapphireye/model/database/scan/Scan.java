package com.zapphireye.zapphireye.model.database.scan;


import com.google.gson.annotations.SerializedName;
import com.zapphireye.zapphireye.model.database.assessment.Risk;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Scan {
    @Id
    private String id;
    @SerializedName(value = "@generated")
    private String date;
    private Site site;
}
