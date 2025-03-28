package com.og.transactioninsight.dto;

import org.json.JSONObject;

import com.og.transactioninsight.entity.TransactionInsights;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionInsightsDTO {

    private String insightType;
    private String insightDescription;
    private JSONObject additionalDetails;
    
    public static TransactionInsightsDTO from(TransactionInsights transactionInsights) {

        // Map fields of transactionInsights to transactionInsightsDTO
        TransactionInsightsDTO transactionInsightsDTO = new TransactionInsightsDTO();
        transactionInsightsDTO.setInsightType(transactionInsights.getInsightType().name());
        transactionInsightsDTO.setInsightDescription(transactionInsights.getInsightDescription());
        transactionInsightsDTO.setAdditionalDetails(transactionInsights.getAdditionalDetails());
        return transactionInsightsDTO;
    }
    
}
