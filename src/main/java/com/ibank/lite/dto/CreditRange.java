package com.ibank.lite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditRange {
    private int minSum;
    private int maxSum;
}
