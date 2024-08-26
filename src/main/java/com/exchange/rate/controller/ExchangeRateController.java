package com.exchange.rate.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.rate.model.ExchangeRate;
import com.exchange.rate.service.ExchangeRateService;

@RestController
@RequestMapping("/fx")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public Map<String, Object> getLatestRate(@RequestParam(required = false, defaultValue = "EUR") String targetCurrency) {
        ExchangeRate rate = exchangeRateService.getLatestRate(targetCurrency);
        return Map.of(
                "date", rate.getDate(),
                "source", rate.getSourceCurrency(),
                "rates", List.of(Map.of(
                    "target", rate.getTargetCurrency(),
                    "value", rate.getRate()
                ))
        );
    }

    @GetMapping("/{targetCurrency}")
    public Map<String, Object> getLatestRatesSeries(@PathVariable String targetCurrency) {
        List<ExchangeRate> rates = exchangeRateService.getLatestRatesSeries(targetCurrency);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("source", "USD");

        Map<String, Object> rateMap = new LinkedHashMap<>();
        for (ExchangeRate rate : rates) {
            rateMap.put(rate.getDate().toString(), Map.of(
                "target", rate.getTargetCurrency(),
                "value", rate.getRate()
            ));
        }

        response.put("rates", rateMap);
        return response;
    }
}
