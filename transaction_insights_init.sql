-- insights_init.sql

CREATE TABLE IF NOT EXISTS transaction_insights (
    insight_id BIGINT PRIMARY KEY,
    transaction_id BIGINT NOT NULL,  -- Application validates existence via Redis cache + Transaction Service events
    insight_type VARCHAR(255) NOT NULL,
    insight_description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_insight_type ON transaction_insights (insight_type);