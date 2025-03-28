package com.og.transactioninsight.listener;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.og.transactioninsight.config.RabbitMQConfig;
import com.og.transactioninsight.dto.TransactionEvent;
import com.og.transactioninsight.entity.AssociatedTransactionId;
import com.og.transactioninsight.entity.InsightType;
import com.og.transactioninsight.entity.TransactionInsights;
import com.og.transactioninsight.repository.TransactionInsightsRepository;


@Service
public class TransactionEventListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionEventListenerService.class);

    @Autowired
    private TransactionInsightsRepository transactionInsightsRepository;

    @RabbitListener(queues = RabbitMQConfig.CREATED_QUEUE)
    public void handleTransactionCreated(TransactionEvent transaction) {
        LOGGER.info("Received TransactionCreated event for transactionId: {}", transaction.getTransactionId());

        // Create insights object if not already exists
        createInsightsObject(transaction);
    }

    @RabbitListener(queues = RabbitMQConfig.DELETED_QUEUE)
    public void handleTransactionDeleted(TransactionEvent transaction) {
        LOGGER.info("Received TransactionDeleted event for transactionId: {}", transaction.getTransactionId());
    }

    private void createInsightsObject(TransactionEvent transaction) {

        // Check if insight is not created for the transaction
         Optional<TransactionInsights> existingInsight = transactionInsightsRepository.findByInsightType(transaction.getType());

        if (!existingInsight.isPresent()) {
            TransactionInsights transactionInsights = new TransactionInsights();
            transactionInsights.setInsightType(InsightType.fromString(transaction.getType()));
            transactionInsights.setInsightDescription("Some description");
            transactionInsights.setCreatedAt(new Date());
            transactionInsights.setUpdatedAt(new Date());

            AssociatedTransactionId associatedTransactionId = new AssociatedTransactionId();
            associatedTransactionId.setTransactionId(transaction.getTransactionId());
            associatedTransactionId.setTransactionInsights(transactionInsights);

            transactionInsights.getAssociatedTransactionIds().add(associatedTransactionId);

            transactionInsightsRepository.save(transactionInsights);
            LOGGER.info("Created transaction insight transactionId: {}", transaction.getTransactionId());

        }
    }
}