package com.og.transactioninsight.service.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class TransactionEvent {

    private Long transactionId;
    private String type;
    private BigDecimal amount;
    private Date transactionDate;

}
