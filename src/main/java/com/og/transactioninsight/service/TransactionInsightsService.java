package com.og.transactioninsight.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.og.transactioninsight.dto.SelectionSetDTO;
import com.og.transactioninsight.dto.TransactionInsightsDTO;
import com.og.transactioninsight.entity.TransactionInsights;
import com.og.transactioninsight.repository.TransactionInsightsRepository;

public class TransactionInsightsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInsightsService.class);

    @Autowired
    private TransactionInsightsRepository transactionInsightsRepository;

    public TransactionInsightsDTO getInsightsByQuery(SelectionSetDTO query) {
        Optional<TransactionInsights> insight = transactionInsightsRepository.findByInsightType(query.getSelectionQuery().getString("insightType"));

        // If Present, convert to DTO
        if (insight.isPresent()) {
            LOGGER.info("Found insight for type: {}", query.getSelectionQuery().getString("insightType"));
            return TransactionInsightsDTO.from(insight.get());
        }

        // Else return Empty DTO
        LOGGER.info("No insight found for type: {}", query.getSelectionQuery().getString("insightType"));
        return new TransactionInsightsDTO();
    }

    public List<TransactionInsightsDTO> getAllInsights() {
        // Fetch all insights from repository
        List<TransactionInsights> insights = transactionInsightsRepository.findAll();

        // Convert to DTO
        return insights.stream().map(TransactionInsightsDTO::from).collect(Collectors.toList());
    }
    
}
