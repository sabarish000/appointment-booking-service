package com.enpal.abs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailableSlotResponse {
    @JsonProperty("available_count")
    private int availableCount;
    @JsonProperty("start_date")
    private String startDate;
}
