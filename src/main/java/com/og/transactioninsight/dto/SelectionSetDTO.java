package com.og.transactioninsight.dto;

import org.json.JSONObject;

import lombok.Getter;

@Getter
public class SelectionSetDTO {

    private Long id;
    private String name;
    private String description;
    private JSONObject selectionQuery;
}
