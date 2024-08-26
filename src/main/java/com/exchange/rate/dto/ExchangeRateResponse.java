package com.exchange.rate.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExchangeRateResponse {
	@Getter @Setter private String date;
	@Getter @Setter private Map<String, BigDecimal> rates;
    
    
}
