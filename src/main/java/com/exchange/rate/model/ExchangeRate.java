package com.exchange.rate.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter private LocalDate date;
    @Getter @Setter private String sourceCurrency;
    @Getter @Setter private String targetCurrency;
    @Getter @Setter private BigDecimal rate;

    
	/*
	 * public ExchangeRate(LocalDate localdate, String scurrency, String tcurrency,
	 * BigDecimal rate) { this.date = localdate; this.sourceCurrency = scurrency;
	 * this.targetCurrency=tcurrency; this.rate=rate; }
	 */
    
}