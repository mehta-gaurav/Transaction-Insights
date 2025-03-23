package com.og.transactioninsight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.og.transactioninsight.entity.TransactionInsights;

public interface TransactionInsightsRepository extends JpaRepository<TransactionInsights, Long> {

    Optional<TransactionInsights> findByInsightType(String type);
    
}
