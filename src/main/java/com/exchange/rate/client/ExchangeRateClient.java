package com.exchange.rate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.exchange.rate.dto.ExchangeRateResponse;

	@FeignClient(name = "exchangeRateClient", url = "https://api.frankfurter.app")
	public interface ExchangeRateClient {
	@GetMapping("/latest")
	public	ExchangeRateResponse getLatestRates(@RequestParam("to") String targetCurrency); 
	
	@GetMapping("/{date}")
	public ExchangeRateResponse getRatesByDate(@PathVariable String date, @RequestParam("to") String targetCurrency); 
	}
