package com.zapphireye.zapphireye.model.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Spider {
    @Id
    private String id;
    private List<Url> url;
}
