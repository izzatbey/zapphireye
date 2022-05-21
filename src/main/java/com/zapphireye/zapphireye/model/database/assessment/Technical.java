package com.zapphireye.zapphireye.model.database.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technical {
    private int confidentality;
    private int integrity;
    private int availability;
    private int accountability;
}
