package com.p2.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")//Which Remote service Rest you want to Use and that host:port
//@FeignClient(name="currency-exchange-service")//Removed host:port as we want to multiple instances of exchange-service based on load
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    /*@GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);*/
    //Above is without Zuul gateway

    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
