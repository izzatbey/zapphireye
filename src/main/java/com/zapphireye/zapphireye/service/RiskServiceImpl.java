package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.assessment.Risk;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@AllArgsConstructor
@Service
public class RiskServiceImpl implements RiskService{


    @Override
    public Risk calculateRisk(Risk risk) {
        // TODO : Calculate Risk
        float threatAgent;
        float vulnerability;
        float technical;
        float business;
        float likelihood;
        float impact;
        float riskRate;
        float threatAgentSum;
        String likeLevel = "";
        String impactLevel = "";
        String riskLevel = "";

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        System.out.println("Motive : " + df.format(risk.getThreatAgent().getMotive()));
        System.out.println("Opportunity : " + df.format(risk.getThreatAgent().getOpportunity()));
        System.out.println("Population : " + df.format(risk.getThreatAgent().getPopulation()));
        System.out.println("Skill : " + df.format(risk.getThreatAgent().getSkill()));
        threatAgentSum = risk.getThreatAgent().getMotive() + risk.getThreatAgent().getOpportunity() +
                risk.getThreatAgent().getPopulation() + risk.getThreatAgent().getSkill();
        threatAgent = threatAgentSum / 4;
        vulnerability = (risk.getVulnerability().getDiscovery() + risk.getVulnerability().getExploit() +
                risk.getVulnerability().getAwareness() + risk.getVulnerability().getIntrusion()) / 4;
        likelihood = (threatAgent + vulnerability) / 2;
        if(likelihood <= 3) {
            likeLevel = "Low";
        } else if (likelihood > 3 && likelihood <= 6 ) {
            likeLevel = "Medium";
        } else if (likelihood > 6 && likelihood <=9) {
            likeLevel = "High";
        }
        technical = (risk.getTechnical().getConfidentality() + risk.getTechnical().getIntegrity() +
                risk.getTechnical().getAvailability() + risk.getTechnical().getAccountability()) / 4;
        business = (risk.getBusiness().getFinancial() + risk.getBusiness().getReputation() +
                risk.getBusiness().getCompliance() + risk.getBusiness().getPrivacy()) / 4;
        impact = (technical + business) / 2;
        if(impact <= 3) {
            impactLevel = "Low";
        } else if (impact > 3 && impact <= 6) {
            impactLevel = "Medium";
        } else if (impact > 6 && impact <= 9) {
            impactLevel = "High";
        }
        
        if(likeLevel == "Low" && impactLevel == "Low") {
            riskLevel = "Note";
        } else if ((likeLevel == "Low" && impactLevel == "Medium") || (likeLevel == "Medium" && impactLevel == "Low")) {
            riskLevel = "Low";
        } else if ((likeLevel == "High" && impactLevel == "Low") || (likeLevel == "Medium" && impactLevel == "Medium") || (likeLevel == "Low" && impactLevel == "High")) {
            riskLevel = "Medium";
        } else if ((likeLevel == "Medium" && impactLevel == "High") || (likeLevel == "High" && impactLevel == "Medium")) {
            riskLevel = "High";
        } else if (likeLevel == "High" && impactLevel == "High") {
            riskLevel = "Critical";
        }

        System.out.println("Threat Agent : " + threatAgent);
        System.out.println("Vulnerability : " + vulnerability);
        System.out.println("Technical : " + technical);
        System.out.println("Business : " + business);
        System.out.println("Likelihood : " + likelihood);
        System.out.println("Impact : " + impact);
        riskRate = (likelihood + impact) / 2;
        System.out.println("Risk Rate : " + riskRate);
        risk.setTotal(riskRate);
        risk.setRate(riskLevel);
        return risk;
    }
}
