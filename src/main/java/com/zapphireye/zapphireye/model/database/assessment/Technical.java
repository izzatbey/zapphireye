package com.zapphireye.zapphireye.model.database.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technical {
    private float confidentality;
    private float integrity;
    private float availability;
    private float accountability;
}
