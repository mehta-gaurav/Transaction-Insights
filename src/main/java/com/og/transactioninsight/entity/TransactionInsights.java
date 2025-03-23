package com.og.transactioninsight.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transaction_insights")
public class TransactionInsights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insightId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsightType insightType;

    @Column(nullable = false)
    private String insightDescription;

    @OneToMany(mappedBy = "transactionInsights", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssociatedTransactionId> associatedTransactionIds = new ArrayList<>();

    @Column(columnDefinition = "jsonb")
    private JSONObject additionalDetails;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}