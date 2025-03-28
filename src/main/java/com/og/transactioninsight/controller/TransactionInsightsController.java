package com.og.transactioninsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.og.transactioninsight.dto.SelectionSetDTO;
import com.og.transactioninsight.dto.TransactionInsightsDTO;
import com.og.transactioninsight.service.TransactionInsightsService;

@RestController
@RequestMapping("/insights")
public class TransactionInsightsController {

    @Autowired
    private TransactionInsightsService transactionInsightsService;

    @PostMapping("/query")
    public ResponseEntity<TransactionInsightsDTO> queryInsights(@RequestBody SelectionSetDTO query) {
        TransactionInsightsDTO insights = transactionInsightsService.getInsightsByQuery(query);
        return ResponseEntity.ok(insights);
    }
    
}
