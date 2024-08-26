package com.exchange.rate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exchange.rate.model.ExchangeRate;
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findTopByTargetCurrencyOrderByDateDesc(String targetCurrency);
    List<ExchangeRate> findTop3ByTargetCurrencyOrderByDateDesc(String targetCurrency);
}