package com.zapphireye.zapphireye.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    private String id;
    private String name;
    private String url;
    private String operator;
    private String period;
    private String desc;
}
