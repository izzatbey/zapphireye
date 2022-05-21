package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.assessment.Risk;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RiskServiceImpl implements RiskService{
    @Override
    public Risk calculateRisk(Risk risk) {
        // TODO : Calculate Risk

        float num = 8;
        String desc = "High";
        risk.setTotal(num);
        risk.setRate(desc);
        return risk;
    }
}
