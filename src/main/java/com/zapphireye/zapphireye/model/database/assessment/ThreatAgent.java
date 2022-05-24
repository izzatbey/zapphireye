package com.zapphireye.zapphireye.model.database.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreatAgent {
    private float skill;
    private float motive;
    private float opportunity;
    private float population;
}
