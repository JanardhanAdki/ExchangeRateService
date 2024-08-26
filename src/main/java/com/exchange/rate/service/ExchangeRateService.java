package com.exchange.rate.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.rate.client.ExchangeRateClient;
import com.exchange.rate.dto.ExchangeRateResponse;
import com.exchange.rate.model.ExchangeRate;
import com.exchange.rate.repository.ExchangeRateRepository;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRate getLatestRate(String targetCurrency) {
        return exchangeRateRepository.findTopByTargetCurrencyOrderByDateDesc(targetCurrency)
                .orElseGet(() -> fetchAndSaveLatestRate(targetCurrency));
    }

    public List<ExchangeRate> getLatestRatesSeries(String targetCurrency) {
        return exchangeRateRepository.findTop3ByTargetCurrencyOrderByDateDesc(targetCurrency);
    }

    private ExchangeRate fetchAndSaveLatestRate(String targetCurrency) {
        ExchangeRateResponse response = exchangeRateClient.getLatestRates(targetCurrency);
        ExchangeRate rate = new ExchangeRate();
        rate.setDate(LocalDate.parse(response.getDate()));
        rate.setSourceCurrency("USD");
        rate.setTargetCurrency(targetCurrency);
        rate.setRate(response.getRates().get(targetCurrency));
        return exchangeRateRepository.save(rate);
    }
}
