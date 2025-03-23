package com.og.transactioninsight.entity;

public enum InsightType {
    TOTAL_TRANSACTION_VALUE,
    LARGE_TRANSACTIONS,
    UNUSUAL_ACTIVITY,
    HIGH_RISK_TRANSACTIONS;

    public static InsightType fromString(String text) {
        for (InsightType type : InsightType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in InsightType enum");
    }
}

