package com.og.transactioninsight.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
@Table(name = "associated_transaction_ids")
public class AssociatedTransactionId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "insight_id") // Foreign key to TransactionInsights
    private TransactionInsights transactionInsights;
}
