package com.zapphireye.zapphireye.model.database.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreatAgent {
    private int skill;
    private int motive;
    private int opportunity;
    private int population;
}
