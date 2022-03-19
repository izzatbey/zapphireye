package com.zapphireye.zapphireye.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instance {
    private String uri;
    private String method;
    private String param;
    private String attack;
    private String evidence;
}
