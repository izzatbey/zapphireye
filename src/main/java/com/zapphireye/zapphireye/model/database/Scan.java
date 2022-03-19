package com.zapphireye.zapphireye.model.database;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Scan {
    @Id
    private String id;
    @SerializedName(value = "@generated")
    private String date;
    private Site site;
}
